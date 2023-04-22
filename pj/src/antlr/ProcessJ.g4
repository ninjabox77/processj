grammar ProcessJ;

@lexer::members {
 public static final int CHANNEL_COMMENT = 1;
 public static final int CHANNEL_COMMENTS = 2;
}

init
 : packageDeclaration? importDeclaration* typeDeclaration* EOF
// : statement* EOF
 ;

packageDeclaration
 : PACKAGE packageAccess SEMI
 ;

importDeclaration
 : singleImportDeclaration
 | multiImportDeclaration
 | multiImportDeclarationStar
 ;

singleImportDeclaration
 : IMPORT annotation? Identifier (DCOLON Identifier)? SEMI
 ;

multiImportDeclaration
 : IMPORT annotation? Identifier (DOT Identifier)* (DCOLON Identifier)? SEMI
 ;

multiImportDeclarationStar
 : IMPORT annotation? Identifier (DOT Identifier)*  DOT MULT SEMI
 ;

typeDeclaration
 : procedureDeclaration     # ProcedureDeclaration_
 | recordDeclaration        # RecordDeclaration_
 | protocolDeclaration      # ProtocolDeclaration_
 | constantDeclaration      # ConstantDeclaration_
 | externDeclaration        # ExternDeclaration_
 ;

procedureDeclaration
 : modifier* type_ Identifier LPAREN formalDeclarationList? RPAREN blockExpression
 ;

modifier
 : MOBILE
 | NATIVE
 | PUBLIC
 | PRIVATE
 | PROTECTED
 | CONST
 | EXTERN
 ;

recordDeclaration
 : modifier* RECORD Identifier extends? recordBody
 ;

extends
 : EXTENDS typeVariable (COMMA typeVariable)*
 ;

implements
 : IMPLEMENTS typeVariable (COMMA typeVariable)*
 ;

recordBody
 : LBRACE recordField* RBRACE
 ;

recordField
 : type_ variableDeclaratorList SEMI
 ;

protocolDeclaration
 : modifier* PROTOCOL Identifier extends? protocolBody
 | modifier* PROTOCOL Identifier extends? SEMI
 ;

protocolBody
 : LBRACE protocolCase* RBRACE
 ;

protocolCase
 : Identifier COLON recordBody
 ;

constantDeclaration
 : modifier* type_ variableDeclaratorList SEMI
 ;

packageAccess
 : Identifier (DOT Identifier)*
 ;

statement
 : SEMI
 | localVariableDeclarationStatement
 | expressionStatement
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
 : variableDeclaratorIdent (EQ variableInitializer)?
 ;

variableDeclaratorIdent
 : Identifier QUEST? dims?
 ;

type_
 : primitiveType
 | referenceType
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

channelType
 : SHARED READ CHAN LT type_ GT
 | SHARED WRITE CHAN LT type_ GT
 | SHARED CHAN LT type_ GT
 | CHAN LT type_ GT
 | CHAN LT type_ GT DOT READ
 | CHAN LT type_ GT DOT WRITE
 | SHARED CHAN LT type_ GT DOT READ
 | SHARED CHAN LT type_ GT DOT WRITE
 ;

typeVariable
 : annotation* Identifier
 | annotation* packageAccess DCOLON Identifier
 | annotation* packageAccess DOT Identifier
 ;

dims
 : (LBRACK RBRACK)+
 ;

variableInitializer
 : expression
 ;

expressionStatement
 : expression SEMI?
 | expressionWithBlock SEMI?
 ;

expression
 : annotation+ expression
 | literalExpression
 | pathExpression
 | expression DOT pathExpression LPAREN actualDeclarationList? RPAREN
 | expression QUEST? DOT Identifier
 | expression DOT SYNC LPAREN RPAREN
 | expression DOT (READ | WRITE) LPAREN expression? RPAREN
 | expression DOT (READ | WRITE)
 | expression DOT TIMEOUT LPAREN actualDeclarationList? RPAREN
 | expression LPAREN actualDeclarationList? RPAREN
 | expression op=(DMINUS | DPLUS)
 | expression LBRACK expression RBRACK
 | expression QUEST expression COLON expression
 | op=(COMP | NOT) expression
 | expression DMULT expression
 | op=(DMINUS | DPLUS) expression
 | LPAREN (expression | primitiveType) RPAREN expression
 | expression op=(MULT | DIV | MOD) expression
 | expression op=(PLUS | MINUS) expression
 | expression op=(LSHIFT | RSHIFT | RRSHIFT) expression
 | expression AND expression
 | expression XOR expression
 | expression OR expression
 | expression comparisonOperator expression
 | expression ANDAND expression
 | expression OROR expression
 | expression EQ expression
 | expression assignmentOperator expression
 | CONTINUE Identifier?
 | BREAK Identifier?
 | RETURN expression?
 | (SKIP_ | STOP)
 | LPAREN expression RPAREN
 | LBRACE arrayElements RBRACE
 | recordExpression
 | protocolExpression
 | externalExpression
 | expressionWithBlock
 ;

annotation
 : normalAnnotation
 | markerAnnotation
 | singleElementAnnotation
 ;

normalAnnotation
 : AT Identifier LPAREN elementValuePairList RPAREN
 ;

elementValuePairList
 : elementValuePair (COMMA elementValuePair)*
 ;

elementValuePair
 : Identifier EQ elementValue
 ;

elementValue
 : literalExpression
 | annotation
 ;

markerAnnotation
 : AT Identifier
 ;

singleElementAnnotation
 : AT Identifier LPAREN elementValue RPAREN
 ;

literalExpression
 : IntegerLiteral
 | FloatingPointLiteral
 | BooleanLiteral
 | CharacterLiteral
 | StringLiteral
 | NullLiteral
 ;

pathExpression
 : DCOLON? Identifier (DCOLON Identifier)*
 ;

actualDeclarationList
 : annotation? expression (COMMA expression)*
 ;

formalDeclarationList
 : formalDeclarations (COMMA lastFormalDeclaration)?    # FormalDeclaration_
 | lastFormalDeclaration                                # LastFormalDeclaration_
 ;

formalDeclarations
 : formalDeclaration (COMMA formalDeclaration)*
 ;

formalDeclaration
 : variableModifier* type_ variableDeclarator (EQ variableInitializer)?
 ;

variableModifier
 : annotation
 | CONST
 ;

lastFormalDeclaration
 : annotation? type_ ELLIPSIS Identifier
 ;

comparisonOperator
 : EQEQ
 | NOTEQ
 | LT
 | GT
 | LTEQ
 | GTEQ
 | IS
 ;

assignmentOperator
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

arrayElements
 : expression (COMMA expression)* COMMA?
 ;

expressionWithBlock
 : blockExpression
 | loopExpression
 | ifExpression
 | switchExpression
 | altExpression
 | parBlockExpression
 ;

blockExpression
 : LBRACE statements? RBRACE
 ;

statements
 : statement+ expression?
 | expression
 ;

recordExpression
 : NEW? typeVariable LBRACE recordExpressionList* RBRACE
 ;

recordExpressionList
 : annotation? DOT? Identifier EQ expression COMMA?
 ;

protocolExpression
 : NEW? typeVariable LBRACE protocolExpressionList RBRACE
 ;

protocolExpressionList
 : DOT? Identifier tagExpressionList (COMMA DOT? Identifier tagExpressionList)*
 ;

tagExpressionList
 : COLON LBRACE recordExpressionList* RBRACE
 ;

externalExpression
 : NEW typeVariable LPAREN actualDeclarationList? RPAREN
 ;

loopExpression
 : forLoopExpression
 | whileLoopExpression
 | doWhileLoopExpression
 | infiniteLoopExpression
 ;

forLoopExpression
 : PAR? FOR LPAREN forInit? SEMI expression? SEMI forUpdate? RPAREN blockExpression
 ;

forInit
 : statementExpressionList
 | localVariableDeclaration
 ;

forUpdate
 : statementExpressionList
 ;

statementExpressionList
 : expression (COMMA expression)*
 ;

whileLoopExpression
 : WHILE LPAREN expression RPAREN blockExpression
 ;

doWhileLoopExpression
 : DO blockExpression WHILE LPAREN expression RPAREN
 ;

infiniteLoopExpression
// : LOOP blockExpression
 : FOR blockExpression
 ;

ifExpression
 : IF LPAREN expression RPAREN blockExpression (ELSE (blockExpression | ifExpression))?
 ;

switchExpression
 : SWITCH LPAREN expression RPAREN caseBlockExpression
 ;

caseBlockExpression
 : LBRACE caseBlockGroupExpression* caseLabel* RBRACE
 ;

caseBlockGroupExpression
 : caseLabel* (statements | expression)
 ;

caseLabel
 : CASE expression COLON
 | DEFAULT COLON
 ;

altExpression
 : priAltExpression
 | repAltLoopExpression
 ;

priAltExpression
 : PRI? ALT altBlodyExpression
 ;

altBlodyExpression
 : LBRACE altCase* RBRACE
 ;

altCase
 : expression ANDAND guardExpression COLON statements
 | guardExpression COLON statements
 | altExpression
 ;

guardExpression
 : expression
 | SKIP_
 ;

repAltLoopExpression
 : PRI? ALT LPAREN forInit? SEMI expression? SEMI forUpdate? RPAREN altBlodyExpression
 ;

parBlockExpression
 : PAR (ENROLL barrierExpression)? blockExpression
 ;

barrierExpression
 : expression (COMMA expression)*
 ;

externDeclaration
 : EXTERN Identifier EQ externType SEMI
 ;

externType
 : typeVariable
 | classType
 ;

classType
 : annotation* Identifier typeArguments?
 | classType DOT annotation* Identifier typeArguments?
 ;

typeArguments
 : LT typeArgumentList GT
 ;

typeArgumentList
 : typeArgument (COMMA typeArgument)*
 ;

typeArgument
// : referenceType
 : classType
 ;

// Keywords
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
EXTERN      : 'extern';

// Integer Literals
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

// Floating-Point Literals
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

// Boolean Literals
BooleanLiteral
 : 'true'
 | 'false'
 ;

// Character Literals
CharacterLiteral
 : '\'' SingleCharacter '\''
 | '\'' EscapeSequence '\''
 ;

fragment
SingleCharacter: ~['\\\r\n] ;

// String Literals
StringLiteral: '"' StringCharacters? '"' ;

fragment
StringCharacters: StringCharacter+ ;

fragment
StringCharacter
 : ~["\\\r\n]
 | EscapeSequence
 ;

// Escape Sequences for Character and String Literals
fragment
EscapeSequence
 : '\\' [btnfr"'\\]
 | OctalEscape
 | UnicodeEscape // This is not in the spec but prevents having to preprocess the input
 ;

fragment
OctalEscape
 : '\\' OctalDigit
 | '\\' OctalDigit OctalDigit
 | '\\' ZeroToThree OctalDigit OctalDigit
 ;

fragment
ZeroToThree: [0-3] ;

// This is not in the spec but prevents having to preprocess the input
fragment
UnicodeEscape: '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit ;

// Separators
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

// Operators
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
LARROW      : '<-';
RARROW      : '->';

GT          : '>';
LT          : '<';
EQEQ        : '==';
LTEQ        : '<=';
GTEQ        : '>=';
NOTEQ       : '!=';

LSHIFT      : '<<';
RSHIFT      : '>>';
RRSHIFT     : '>>>';
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

// Null Literal
NullLiteral: 'null';

// Identifiers (must appear after all keywords in the antlr)
Identifier: JavaLetter JavaLetterOrDigit* ;

fragment
JavaLetter
 :	[a-zA-Z$_] // these are the "java letters" below 0x7F
 | // covers all characters above 0x7F which are not a surrogate
   ~[\u0000-\u007F\uD800-\uDBFF]
   { Character.isJavaIdentifierStart(_input.LA(-1)) }?
 | // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
   [\uD800-\uDBFF] [\uDC00-\uDFFF]
   { Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1))) }?
 ;

fragment
JavaLetterOrDigit
 :	[a-zA-Z0-9$_] // these are the "java letters or digits" below 0x7F
 | // covers all characters above 0x7F which are not a surrogate
   ~[\u0000-\u007F\uD800-\uDBFF]
   { Character.isJavaIdentifierPart(_input.LA(-1)) }?
 | // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
   [\uD800-\uDBFF] [\uDC00-\uDFFF]
   { Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1))) }?
 ;

fragment
ShCommand:  ~[\r\n\uFFFF]* ;

// Whitespace and ast.comments
WS:  [ \t\r\n\u000C]+ -> skip ;
COMMENT:   '/*' .*? '*/' -> channel(2) ; // as CHANNEL_COMMENTS
LINE_COMMENT:   '//' ~[\r\n]* -> channel(1) ; // as CHANNEL_COMMENT