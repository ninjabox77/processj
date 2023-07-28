grammar ProcessJ;

@lexer::members {
 public static final int CHANNEL_COMMENT = 1;
 public static final int CHANNEL_COMMENTS = 2;
}

compilationUnit
 : packageDeclaration? importDeclaration* typeDeclaration* EOF
 ;

packageDeclaration
 : packageModifier* PACKAGE packageName SEMI
 ;

packageModifier
 : annotation
 ;

packageName
 : Identifier
 | packageName DOT Identifier
 ;

importDeclaration
 : singleTypeImportDeclaration
 | singleTypeMultiImportDeclaration
 | singleStaticImportDeclaration
 | singleStaticMultiImportDeclaration
 ;

singleTypeImportDeclaration
 : IMPORT annotation* typeName DCOLON Identifier SEMI
 ;

singleTypeMultiImportDeclaration
 : IMPORT annotation* typeName DOT MULT SEMI
 ;

singleStaticImportDeclaration
 : IMPORT annotation* STATIC typeName DOT Identifier SEMI
 ;

singleStaticMultiImportDeclaration
 : IMPORT annotation* STATIC typeName DOT MULT SEMI
 ;

typeDeclaration
 : procedureTypeDeclaration
 | recordTypeDeclaration
 | protocolTypeDeclaration
 | constantDeclaration
 | externDeclaration
 ;

externDeclaration
 : modifier* EXTERN Identifier EQ classType SEMI
 ;

procedureTypeDeclaration
 : modifier* type_ Identifier LPAREN formarlParameterList? RPAREN body?
 ;

modifier
 : MOBILE
 | CONST
 | NATIVE
 | PUBLIC
 | PRIVATE
 | PROTECTED
 ;

formarlParameterList
 : formalParameters COMMA lastFormalDeclaration
 | lastFormalDeclaration
 ;

formalParameters
 : formalParameter (COMMA formalParameter)*
 ;

formalParameter
 : variableModifier* type_ variableDeclaratorIdentifier
 ;

variableModifier
 : annotation
 | CONST
 ;

lastFormalDeclaration
 : variableModifier* type_ annotation* ELLIPSIS variableDeclaratorIdentifier
 | formalParameter
 ;

variableDeclaratorIdentifier
 : Identifier dims?
 ;

body
 : block
 | SEMI
 ;

recordTypeDeclaration
 : modifier* RECORD Identifier extends? recordBody
 ;

extends
 : EXTENDS typeVariable (COMMA typeVariable)*
 ;

recordBody
 : LBRACE recordMemberDeclaration* RBRACE
 ;

recordMemberDeclaration
 : type_ recordMemberDeclarators SEMI
 ;

recordMemberDeclarators
 : recordMemberDeclarator (COMMA recordMemberDeclarator)*
 ;

recordMemberDeclarator
 : variableDeclaratorIdentifier
 ;

protocolTypeDeclaration
 : modifier* PROTOCOL Identifier extends? (protocolBody | SEMI)
 ;

protocolBody
 : LBRACE protocolCase* RBRACE
 ;

protocolCase
 : Identifier COLON recordBody
 ;

type_
 : primitiveType
 | referenceType
 | annotation* classType
 | VOID
 ;

primitiveType
 : numericType
 | BOOLEAN
 | STRING
 | BARRIER
 | TIMER
 ;

referenceType
 : arrayType
 | channelType
 | typeVariable
 | classType
 ;

numericType
 : integralType
 | floatingPointType
 ;

integralType
 : BYTE
 | SHORT
 | INT
 | LONG
 | CHAR
 ;

floatingPointType
 : FLOAT
 | DOUBLE
 ;

arrayType
 : primitiveType dims
 | channelType dims
 | typeVariable dims
 ;

classType
 : annotation* Identifier typeArguments?                # IdentifierArguments
 | classType DOT annotation* Identifier typeArguments?  # ClassDotIdentifierArguments
 ;

typeArguments
 : LT typeArgument (COMMA typeArgument)* GT
 ;

typeArgument
 : annotation* (classType | primitiveType) (LBRACK RBRACK)*
 ;

channelType
 : SHARED READ CHAN LT type_ GT         # ChannelSharedReadType
 | SHARED WRITE CHAN LT type_ GT        # ChannelSharedWriteType
 | SHARED CHAN LT type_ GT              # ChannelSharedType
 | CHAN LT type_ GT                     # ChannelType_
 | CHAN LT type_ GT DOT READ            # ChannelDotRead
 | CHAN LT type_ GT DOT WRITE           # ChannelDotWrite
 | SHARED CHAN LT type_ GT DOT READ     # ChannelSharedDotRead
 | SHARED CHAN LT type_ GT DOT WRITE    # ChannelSharedDotWrite
 ;

typeVariable
 : Identifier                  # TypeVariableIdentifier
 | typeName DCOLON Identifier  # TypeVariablePackageDotIdentifier
 ;

dims
 : annotation* LBRACK RBRACK (annotation* LBRACK RBRACK)*
 ;

annotation
 : normalAnnotation
 | markerAnnotation
 | singleElementAnnotation
 ;

normalAnnotation
 : AT typeName LPAREN elementValuePairList RPAREN
 ;

elementValuePairList
 : elementValuePair (COMMA elementValuePair)*
 ;

elementValuePair
 : Identifier EQ elementValue
 ;

elementValue
 : expression
 | annotation
 ;

markerAnnotation
 : AT typeName
 ;

singleElementAnnotation
 : AT typeName LPAREN elementValue RPAREN
 ;

typeName
 : Identifier
 | typeName DOT Identifier
 ;

 constantDeclaration
  : modifier* type_ constantDeclarators SEMI
  ;

constantDeclarators
 : constantDeclarator (COMMA constantDeclarator)*
 ;

constantDeclarator
 : variableDeclaratorIdentifier (EQ variableInitializer)?
 ;

variableInitializer
 : arrayInitializer
 | expression
 ;

arrayInitializer
 : LBRACE variableInitializerList? COMMA? RBRACE
 ;

variableInitializerList
 : variableInitializer (COMMA variableInitializer)*
 ;

block
 : LBRACE blockStatement* RBRACE
 ;

blockStatement
 : localVariableDeclarationStatement
 | statement
 ;

localVariableDeclarationStatement
 : localVariableDeclaration SEMI
 ;

localVariableDeclaration
 : variableModifier* type_ variableDeclaratorList
 ;

variableDeclaratorList
 : variableDeclarator (COMMA variableDeclarator)*
 ;

variableDeclarator
 : variableDeclaratorIdentifier (EQ variableInitializer)?
 ;

statement
 : block                                                        # BlockAsStatement
 | ASSERT expression (COLON expression)? SEMI                   # AssertStatement
 | IF parExpression statement (ELSE statement)?                 # IfStatement
 | FOR LPAREN forControl RPAREN statement                       # ForStatement
 | PRI? ALT altBody                                             # AltStatement
 | PRI? ALT LPAREN forControl RPAREN altBody                    # AltForStatement
 | PAR (ENROLL expressionList)? statement                       # ParStatement
 | PAR LPAREN forControl RPAREN statement                       # ParForStatement
 | WHILE parExpression statement                                # WhileStatement
 | DO statement WHILE parExpression SEMI                        # DoStatement
 | SWITCH parExpression LBRACE switchBlockStatementGroup* switchLabel* RBRACE  # SwitchStatement
 | RETURN expression? SEMI                                      # ReturnStatement
 | BREAK Identifier? SEMI                                       # BreakStatement
 | CONTINUE Identifier? SEMI                                    # ContinueStatement
 | SKIP_ SEMI                                                   # SkipStatement
 | STOP SEMI                                                    # StopStatement
 | SEMI                                                         # EmptyStatement
 | expression SEMI                                              # StatementExpression
 | Identifier COLON statement                                   # LabelStatement
 ;

switchBlockStatementGroup
 : switchLabel+ blockStatement+
 ;

switchLabel
 : CASE expression COLON
 | DEFAULT COLON
 ;

forControl
 : enhancedForControl
 | forInit? SEMI expression? SEMI forUpdate=expressionList?
 ;

forInit
 : localVariableDeclaration
 | expressionList
 ;

enhancedForControl
 : variableModifier* type_ variableDeclaratorIdentifier COLON expression
 ;

parExpression
 : LPAREN expression RPAREN
 ;

expressionList
 : expression (COMMA expression)*
 ;

invocation
 : identifier arguments
 ;

arguments
 : LPAREN expressionList? RPAREN
 | LPAREN block RPAREN
 ;

altBody
 : LBRACE altCase altCase* RBRACE
 ;

altCase
 : parExpression ANDAND altGuard COLON statement
 | altGuard COLON statement
 | altStatement=statement
 ;

altGuard
 : SKIP_
 | assignExpr=expression
 ;

expression
 : primary                                                    # PrimaryExpression
 | annotation+ expression                                     # AnnotatedExpression
 | expression LBRACK expression RBRACK                        # ArrayAccexxExpression
 | expression DOT (identifier | invocation)                   # MemberAccessExpression
 | invocation                                                 # InvocationExpression
 | expression op=(DPLUS|DMINUS)                               # PostfixExpression
 | op=(DPLUS|DMINUS) expression                               # PrefixExpression
 | LPAREN primitiveType RPAREN expression                     # CastExpression
 | NEW creator                                                # ObjectCreationExpression
 | expression op=(MULT|DIV|MOD) expression                    # MultiplicativeExpression
 | expression op=(PLUS|MINUS) expression                      # AdditiveExpression
 | expression ('<' '<'|'>' '>' '>'|'>' '>') expression        # ShiftExpression
 | expression op=(LTEQ|GTEQ|GT|LT) expression                 # RelationalExpression
 | expression IS expression                                   # InstanceofExpression
 | expression op=(EQEQ|NOTEQ) expression                      # EqualityExpression
 | expression AND expression                                  # AndExpression
 | expression XOR expression                                  # ExclusiveExpression
 | expression OR expression                                   # InclusiveExpression
 | expression ANDAND expression                               # LogicalAndExpression
 | expression OROR expression                                 # LogicalOrExpression
 | <assoc=right> expression QUEST expression COLON expression # TernaryExpression
 | <assoc=right> expression assignOp expression               # AssignmentExpression
 ;

assignOp
 : EQ
 | MULTEQ
 | DIVEQ
 | MODEQ
 | PLUSEQ
 | MINUSEQ
 | LSHIFTEQ
 | RSHIFTEQ
 | RRSHIFTEQ
 | ANDEQ
 | XOREQ
 | OREQ
 ;

primary
 : LPAREN expression RPAREN
 | literal
 | identifier
 ;

creator
 : creatorName (
 | arrayExpression
 | recordExpression
 | protocolExpression
 | classExpression)
// | Identifier
 ;

creatorName
 : Identifier
 | primitiveType
 | typeName
 ;

arrayExpression
 : (LBRACK RBRACK)+ arrayInitializer
 | (LBRACK expression RBRACK)+ (LBRACK RBRACK)*
 ;

recordExpression
 : LBRACE recordExpressionList* RBRACE
 ;

recordExpressionList
 : annotation? DOT? Identifier EQ expression COMMA?
 ;

protocolExpression
 : LBRACE protocolExpressionList RBRACE
 ;

protocolExpressionList
 : DOT? Identifier tagExpressionList //(COMMA DOT? Identifier tagExpressionList)*
 ;

tagExpressionList
 : COLON LBRACE? recordExpressionList* RBRACE?
 ;

//externalExpression
// : NEW typeVariable LPAREN arguments? RPAREN
// ;

classExpression
 : typeArgumentsOrDiamond? arguments
 ;

typeArgumentsOrDiamond
 : LT QUEST GT
 | typeArguments
 ;

// Literals.
literal
 : IntegerLiteral
 | FloatingPointLiteral
 | BooleanLiteral
 | CharacterLiteral
 | StringLiteral
 | NullLiteral
 ;

identifier
 : Identifier
 | TIMEOUT
 | READ
 | WRITE
 | SYNC
 ;

// ==----------------------------------------------==
// Lexer stuff.

// Keywords.
BOOLEAN     : 'boolean';
BYTE        : 'byte';
SHORT       : 'short';
INT         : 'int';
LONG        : 'long';
FLOAT       : 'float';
DOUBLE      : 'double';
CHAR        : 'char';
STRING      : 'string';
VOID        : 'void';

CHAN        : 'chan';
READ        : 'read';
WRITE       : 'write';
SHARED      : 'shared';
CLAIM       : 'claim';
ASSERT      : 'assert';

BARRIER     : 'barrier';
SYNC        : 'sync';
ENROLL      : 'enroll';

TIMER       : 'timer';
TIMEOUT     : 'timeout';

SKIP_       : 'skip';
STOP        : 'stop';
IS          : 'is';
PRAGMA      : '#pragma';
AT          : '@';

IF          : 'if';
ELSE        : 'else';
FOR         : 'for';
WHILE       : 'while';
SWITCH      : 'switch';
CASE        : 'case';
DO          : 'do';
LOOP        : 'loop';
DEFAULT     : 'default';
BREAK       : 'break' ;
CONTINUE    : 'continue' ;
RETURN      : 'return' ;

SEQ         : 'seq';
PAR         : 'par';
PRI         : 'pri';
ALT         : 'alt';
FORK        : 'fork';

NEW         : 'new';
RESUME      : 'resume';
SUSPEND     : 'suspend';
WITH        : 'with';
AS          : 'as';

PROC        : 'proc';
PROTOCOL    : 'protocol';
RECORD      : 'record';
EXTENDS     : 'extends';
IMPLEMENTS  : 'implements';

PACKAGE     : 'package';
IMPORT      : 'import';

MOBILE      : 'mobile';
NATIVE      : 'native';
PUBLIC      : 'public';
PRIVATE     : 'private';
PROTECTED   : 'protected';
CONST       : 'const';
STATIC      : 'static';
EXTERN      : 'extern';

// Integer literals.
IntegerLiteral
 : DecimalIntegerLiteral
 | HexIntegerLiteral
 | OctalIntegerLiteral
 | BinaryIntegerLiteral
 ;

fragment
DecimalIntegerLiteral: DecimalNumeral IntegerTypeSuffix? ;

fragment
HexIntegerLiteral: HexNumeral IntegerTypeSuffix? ;

fragment
OctalIntegerLiteral: OctalNumeral IntegerTypeSuffix? ;

fragment
BinaryIntegerLiteral: BinaryNumeral IntegerTypeSuffix? ;

fragment
IntegerTypeSuffix: [lL] ;

fragment
DecimalNumeral
 : '0'
 | NonZeroDigit (Digits?
 | Underscores Digits)
 ;

fragment
Digits: Digit (DigitsAndUnderscores? Digit)? ;

fragment
Digit
 : '0'
 | NonZeroDigit
 ;

fragment
NonZeroDigit: [1-9] ;

fragment
DigitsAndUnderscores: DigitOrUnderscore+ ;

fragment
DigitOrUnderscore
 : Digit
 | '_'
 ;

fragment
Underscores: '_'+ ;

fragment
HexNumeral: '0' [xX] HexDigits ;

fragment
HexDigits: HexDigit (HexDigitsAndUnderscores? HexDigit)? ;

fragment
HexDigit: [0-9a-fA-F] ;

fragment
HexDigitsAndUnderscores: HexDigitOrUnderscore+ ;

fragment
HexDigitOrUnderscore
 : HexDigit
 | '_'
 ;

fragment
OctalNumeral
 : '0' Underscores? OctalDigits
 ;

fragment
OctalDigits
 : OctalDigit (OctalDigitsAndUnderscores? OctalDigit)?
 ;

fragment
OctalDigit
 : [0-7]
 ;

fragment
OctalDigitsAndUnderscores
 : OctalDigitOrUnderscore+
 ;

fragment
OctalDigitOrUnderscore
 : OctalDigit
 | '_'
 ;

fragment
BinaryNumeral
 : '0' [bB] BinaryDigits
 ;

fragment
BinaryDigits
 : BinaryDigit (BinaryDigitsAndUnderscores? BinaryDigit)?
 ;

fragment
BinaryDigit
 : [01]
 ;

fragment
BinaryDigitsAndUnderscores
 : BinaryDigitOrUnderscore+
 ;

fragment
BinaryDigitOrUnderscore
 : BinaryDigit
 | '_'
 ;

// Floating-Point literals.
FloatingPointLiteral
 : DecimalFloatingPointLiteral
 | HexadecimalFloatingPointLiteral
 ;

fragment
DecimalFloatingPointLiteral
 : Digits '.' Digits? ExponentPart? FloatTypeSuffix?
 | '.' Digits ExponentPart? FloatTypeSuffix?
 | Digits ExponentPart FloatTypeSuffix?
 | Digits FloatTypeSuffix
 ;

fragment
ExponentPart
 : ExponentIndicator SignedInteger
 ;

fragment
ExponentIndicator
 : [eE]
 ;

fragment
SignedInteger: Sign? Digits ;

fragment
Sign: [+-] ;

fragment
FloatTypeSuffix: [fFdD] ;

fragment
HexadecimalFloatingPointLiteral: HexSignificand BinaryExponent FloatTypeSuffix? ;

fragment
HexSignificand
 : HexNumeral '.'?
 | '0' [xX] HexDigits? '.' HexDigits
 ;

fragment
BinaryExponent: BinaryExponentIndicator SignedInteger ;

fragment
BinaryExponentIndicator: [pP] ;

// Boolean literals.
BooleanLiteral
 : 'true'
 | 'false'
 ;

// Character literals.
CharacterLiteral
 : '\'' SingleCharacter '\''
 | '\'' EscapeSequence '\''
 ;

fragment
SingleCharacter: ~['\\\r\n] ;

// String literals.
StringLiteral: '"' StringCharacters? '"' ;

fragment
StringCharacters: StringCharacter+ ;

fragment
StringCharacter
 : ~["\\\r\n]
 | EscapeSequence
 ;

// Escape sequences for character and string literals.
fragment
EscapeSequence
 : '\\' [btnfr"'\\]
 | OctalEscape
 | UnicodeEscape
 ;

fragment
OctalEscape
 : '\\' OctalDigit
 | '\\' OctalDigit OctalDigit
 | '\\' ZeroToThree OctalDigit OctalDigit
 ;

fragment
ZeroToThree: [0-3] ;

// This is not in the spec but prevents having to preprocess the input.
fragment
UnicodeEscape: '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit ;

// Separators.
LPAREN      : '(';
RPAREN      : ')' ;
LBRACE      : '{';
RBRACE      : '}' ;
LBRACK      : '[';
RBRACK      : ']' ;

SEMI        : ';';
COMMA       : ',';

QUEST       : '?';
DCOLON      : '::';
COLON       : ':';
DOT         : '.';
ELLIPSIS    : '...';

// Operators.

EQ          : '=';
MULTEQ      : '*=';
DIVEQ       : '/=';
MODEQ       : '%=';
PLUSEQ      : '+=';
MINUSEQ     : '-=';
LSHIFTEQ    : '<<=';
RSHIFTEQ    : '>>=';
RRSHIFTEQ   : '>>>=';
ANDEQ       : '&=';
XOREQ       : '^=';
OREQ        : '|=';

GT          : '>';
LT          : '<';
EQEQ        : '==';
LTEQ        : '<=';
GTEQ        : '>=';
NOTEQ       : '!=';

//LSHIFT      : '<<';
//RSHIFT      : '>>';
//RRSHIFT     : '>>>';
ANDAND      : '&&';
OROR        : '||';
PLUS        : '+';
MINUS       : '-';
MULT        : '*';
DIV         : '/';
AND         : '&';
OR          : '|';
XOR         : '^';
MOD         : '%';

NOT         : '!';
COMP        : '~';
DPLUS       : '++';
DMINUS      : '--';
DMULT       : '**';

// Null literals.
NullLiteral: 'null';

// Identifiers (must appear after all keywords in the antlr).
Identifier: JavaLetter JavaLetterOrDigit* ;

fragment
JavaLetter
 : [a-zA-Z$_] // these are the "java letters" below 0x7F
 | // covers all characters above 0x7F which are not a surrogate
   ~[\u0000-\u007F\uD800-\uDBFF]
   { Character.isJavaIdentifierStart(_input.LA(-1)) }?
 | // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
   [\uD800-\uDBFF] [\uDC00-\uDFFF]
   { Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1))) }?
 ;

fragment
JavaLetterOrDigit
 : [a-zA-Z0-9$_] // these are the "java letters or digits" below 0x7F
 | // covers all characters above 0x7F which are not a surrogate
   ~[\u0000-\u007F\uD800-\uDBFF]
   { Character.isJavaIdentifierPart(_input.LA(-1)) }?
 | // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
   [\uD800-\uDBFF] [\uDC00-\uDFFF]
   { Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1))) }?
 ;

fragment
ShCommand:  ~[\r\n\uFFFF]* ;

// Whitespace and ast comments.
WS: [ \t\r\n\u000C]+ -> skip ;
COMMENT: '/*' .*? '*/' -> channel(2) ; // as CHANNEL_COMMENTS
LINE_COMMENT: '//' ~[\r\n]* -> channel(1) ; // as CHANNEL_COMMENT