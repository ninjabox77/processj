grammar ProcessJ;

@lexer::members {
 public static final int CHANNEL_COMMENT = 1;
 public static final int CHANNEL_COMMENTS = 2;
}

compilationUnit
 : packageDeclaration? importDeclaration* typeDeclaration* EOF
 ;

packageDeclaration
 : packageModifier* 'package' packageName ';'
 ;

packageModifier
 : annotation
 ;

packageName
 : Identifier
 | packageName '.' Identifier
 ;

importDeclaration
 : singleTypeImportDeclaration
 | singleTypeMultiImportDeclaration
 | singleStaticImportDeclaration
 | singleStaticMultiImportDeclaration
 ;

singleTypeImportDeclaration
 : 'import' annotation* typeName ('::' Identifier)? ';'
 ;

singleTypeMultiImportDeclaration
 : 'import' annotation* typeName '.' '*' ';'
 ;

singleStaticImportDeclaration
 : 'import' annotation* 'static' typeName '.' Identifier ';'
 ;

singleStaticMultiImportDeclaration
 : 'import' annotation* 'static' typeName '.' '*' ';'
 ;

typeDeclaration
 : procedureTypeDeclaration
 | recordTypeDeclaration
 | protocolTypeDeclaration
 | constantDeclaration
 | externDeclaration
 ;

externDeclaration
 : modifier* 'extern' Identifier '=' classType ';'
 ;

procedureTypeDeclaration
 : modifier* type_ Identifier '(' formarlParameterList? ')' (body | ';')
 ;

modifier
 : 'mobile'
 | 'const'
 | 'native'
 | 'public'
 | 'private'
 | 'protected'
 ;

formarlParameterList
 : formalParameters ',' lastFormalDeclaration
 | lastFormalDeclaration
 ;

formalParameters
 : formalParameter (',' formalParameter)*
 ;

formalParameter
 : annotation* variableModifier? type_ variableDeclaratorIdentifier
 ;

variableModifier
 : 'const'
 ;

lastFormalDeclaration
 : annotation* variableModifier? type_ annotation* '...' variableDeclaratorIdentifier
 | formalParameter
 ;

variableDeclaratorIdentifier
 : Identifier dims?
 ;

body
 : block
 | ';'
 ;

recordTypeDeclaration
 : modifier* 'record' Identifier extends? recordBody ';'?
 ;

extends
 : 'extends' typeVariable (',' typeVariable)*
 ;

recordBody
 : '{' recordMemberDeclaration* '}'
 ;

recordMemberDeclaration
 : type_ recordMemberDeclarators ';'
 ;

recordMemberDeclarators
 : recordMemberDeclarator (',' recordMemberDeclarator)*
 ;

recordMemberDeclarator
 : variableDeclaratorIdentifier
 ;

protocolTypeDeclaration
 : modifier* 'protocol' Identifier extends? (protocolBody ';'? | ';')
 ;

protocolBody
 : '{' protocolCase* '}'
 ;

protocolCase
 : Identifier ':' recordBody
 ;

type_
 : primitiveType
 | referenceType
 | annotation* classType
 | 'void'
 ;

primitiveType
 : numericType
 | 'boolean'
 | 'string'
 | 'barrier'
 | 'timer'
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
 : 'byte'
 | 'short'
 | 'int'
 | 'long'
 | 'char'
 ;

floatingPointType
 : 'float'
 | 'double'
 ;

arrayType
 : primitiveType dims
 | channelType dims
 | typeVariable dims
 ;

classType
 : annotation* Identifier typeArguments?                # IdentifierArguments
 | classType '.' annotation* Identifier typeArguments?  # ClassDotIdentifierArguments
 ;

typeArguments
 : '<' typeArgument (',' typeArgument)* '>'
 ;

typeArgument
 : annotation* (classType | primitiveType) ('[' ']')*
 ;

channelType
 : 'shared' 'read' 'chan' '<' type_ '>'         # ChannelSharedReadType
 | 'shared' 'write' 'chan' '<' type_ '>'        # ChannelSharedWriteType
 | 'shared' 'chan' '<' type_ '>'                # ChannelSharedType
 | 'chan' '<' type_ '>'                         # ChannelType_
 | 'chan' '<' type_ '>' '.' 'read'              # ChannelDotRead
 | 'chan' '<' type_ '>' '.' 'write'             # ChannelDotWrite
 | 'shared' 'chan' '<' type_ '>' '.' 'read'     # ChannelSharedDotRead
 | 'shared' 'chan' '<' type_ '>' '.' 'write'    # ChannelSharedDotWrite
 ;

typeVariable
 : Identifier
 | typeName '::' Identifier
 ;

dims
 : annotation* '[' ']' (annotation* '[' ']')*
 ;

annotation
 : normalAnnotation
 | markerAnnotation
 | singleElementAnnotation
 ;

normalAnnotation
 : '@' typeName '(' elementValuePairList ')'
 ;

elementValuePairList
 : elementValuePair (',' elementValuePair)*
 ;

elementValuePair
 : Identifier '=' elementValue
 ;

elementValue
 : expression
 | annotation
 ;

markerAnnotation
 : '@' typeName
 ;

singleElementAnnotation
 : '@' typeName '(' elementValue ')'
 ;

typeName
 : Identifier
 | typeName '.' Identifier
 ;

 constantDeclaration
  : modifier* type_ constantDeclarators ';'
  ;

constantDeclarators
 : constantDeclarator (',' constantDeclarator)*
 ;

constantDeclarator
 : variableDeclaratorIdentifier ('=' variableInitializer)?
 ;

variableInitializer
 : arrayInitializer
 | expression
 ;

arrayInitializer
 : '{' variableInitializerList? ','? '}'
 ;

variableInitializerList
 : variableInitializer (',' variableInitializer)*
 ;

block
 : '{' blockStatement* '}'
 ;

blockStatement
 : localVariableDeclarationStatement
 | statement
 ;

localVariableDeclarationStatement
 : localVariableDeclaration ';'
 ;

localVariableDeclaration
 : annotation* variableModifier? type_ variableDeclaratorList
 ;

variableDeclaratorList
 : variableDeclarator (',' variableDeclarator)*
 ;

variableDeclarator
 : variableDeclaratorIdentifier ('=' variableInitializer)?
 ;

statement
 : block                                                                       # BlockAsStatement
 | 'assert' expression (':' expression)? ';'                                   # AssertStatement
 | 'if' parExpression statement ('else' statement)?                            # IfStatement
 | 'for' '(' forControl ')' statement                                          # ForStatement
 | 'pri'? 'alt' altBody                                                        # AltStatement
 | 'pri'? 'alt' '(' forControl ')' altBody                                     # AltForStatement
 | 'par' ('enroll' expressionList)? statement                                  # ParStatement
 | 'par' '(' forControl ')' statement                                          # ParForStatement
 | 'while' parExpression statement                                             # WhileStatement
 | 'do' statement 'while' parExpression ';'                                    # DoStatement
 | 'switch' parExpression '{' switchBlockStatementGroup* switchLabel* '}'      # SwitchStatement
 | 'return' expression? ';'                                                    # ReturnStatement
 | 'break' Identifier? ';'                                                     # BreakStatement
 | 'continue' Identifier? ';'                                                  # ContinueStatement
 | 'seq' block                                                                 # SeqStatement
 | 'skip' ';'                                                                  # SkipStatement
 | 'stop' ';'                                                                  # StopStatement
 | ';'                                                                         # EmptyStatement
 | expression ';'                                                              # StatementExpression
 | (Identifier ':')+ statement                                                 # LabelStatement
 ;

switchBlockStatementGroup
 : switchLabel+ blockStatement+
 ;

switchLabel
 : 'case' expression ':'
 | 'default' ':'
 ;

forControl
 : enhancedForControl
 | forInit? ';' expression? ';' forUpdate=expressionList?
 ;

forInit
 : localVariableDeclaration
 | expressionList
 ;

enhancedForControl
 : variableModifier? type_ variableDeclaratorIdentifier ':' expression
 ;

parExpression
 : '(' expression ')'
 ;

expressionList
 : expression (',' expression)*
 ;

invocation
 : identifier arguments
 ;

arguments
 : '(' expressionList? ')'
 | '(' block ')'
 ;

altBody
 : altCase
 | '{' altCase altCase* '}'
 ;

altCase
 : parExpression '&&' altGuard ':' statement
 | altGuard ':' statement
 | altStatement=statement
 ;

altGuard
 : 'skip'
 | assignExpr=expression
 ;

expression
 : primary                                                     # PrimaryExpression
 | annotation+ expression                                      # AnnotatedExpression
 | name=expression '[' index=expression ']'                    # ArrayAccessExpression
 | expression '.' (identifier | invocation)                    # MemberAccessExpression
 | invocation                                                  # InvocationExpression
 | expression op=('++'|'--')                                   # PostfixExpression
 | op=('++'|'--'|'+'|'-'|'~'|'!') expression                   # PrefixExpression
 | '(' primitiveType ')' expression                            # CastExpression
 | 'new' creator                                               # ObjectCreationExpression
 | expression op=('*'|'/'|'%') expression                      # MultiplicativeExpression
 | expression op=('+'|'-') expression                          # AdditiveExpression
 | expression ('<' '<'|'>' '>' '>'|'>' '>') expression         # ShiftExpression
 | expression op=('<='|'>='|'>'|'<') expression                # RelationalExpression
 | expression 'is' expression                                  # InstanceofExpression
 | expression op=('=='|'!=') expression                        # EqualityExpression
 | expression '&' expression                                   # AndExpression
 | expression '^' expression                                   # ExclusiveExpression
 | expression '|' expression                                   # InclusiveExpression
 | expression '&&' expression                                  # LogicalAndExpression
 | expression '||' expression                                  # LogicalOrExpression
 | <assoc=right> expression '?' expression ':' expression      # TernaryExpression
 | <assoc=right> expression assignOp expression                # AssignmentExpression
 ;

assignOp
 : '='
 | '*='
 | '/='
 | '%='
 | '+='
 | '-='
 | '<<='
 | '>>='
 | '>>>='
 | '&='
 | '^='
 | '|='
 ;

primary
 : '(' expression ')'
 | literal
 | identifier
 ;

creator
 : creatorName (
 | arrayExpression
 | recordExpression
 | protocolExpression
 | classExpression)
 ;

creatorName
 : Identifier
 | primitiveType
 | typeName
 ;

arrayExpression
 : ('[' ']')+ arrayInitializer
 | ('[' expression ']')+ ('[' ']')*
 ;

recordExpression
 : '{' recordExpressionList* '}'
 ;

recordExpressionList
 : annotation? '.'? Identifier '=' expression ','?
 ;

protocolExpression
 : '{' protocolExpressionList '}'
 ;

protocolExpressionList
 : '.'? Identifier tagExpressionList
 ;

tagExpressionList
 : ':' '{'? recordExpressionList* '}'?
 ;

//externalExpression
// : NEW typeVariable LPAREN arguments? RPAREN
// ;

classExpression
 : typeArgumentsOrDiamond? arguments
 ;

typeArgumentsOrDiamond
 : '<' '?' '>'
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
 | 'timeout'
 | 'read'
 | 'write'
 | 'sync'
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
EACH        : '@';

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