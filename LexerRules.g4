lexer grammar LexerRules;

// Single characters
L_PAREN        : '(';
R_PAREN        : ')';
L_BRACE        : '{';
R_BRACE        : '}';
COMMA          : ',';
DOT            : '.';
SEMICOLON      : ';';

// Attribution operators
DECLARE_ASSIGN : ':=';
RECEIVE        : '<-';

// Logical operators
LOGICAL_AND : '&&';
LOGICAL_OR  : '||';
LOGICAL_NOT : '!';

// Relational operators
EQUAL         : '==';
NOT_EQUAL     : '!=';
LESS          : '<';
LESS_EQUAL    : '<=';
GREATER       : '>';
GREATER_EQUAL : '>=';

// Arithmetical operators
PLUS        : '+';
MINUS       : '-';
MUL         : '*';
DIV         : '/';

// Keywords
PACKAGE : 'package';
FUNC    : 'func';
IF      : 'if';
ELSE    : 'else';
RETURN  : 'return';
GO      : 'go';
CHAN    : 'chan';
MAKE    : 'make';

// Basic tokens
TYPE     : 'int' | 'string' | 'bool' ;
TRUE     : 'true';
FALSE    : 'false';
ID       : [_a-zA-Z][_a-zA-Z0-9]* ;
INT      : '0' | [1-9][0-9]*;

// Hidden tokens
NEWLINE      : '\r'? '\n' ;
LINE_COMMENT : '//' ~[\r\n]*; 
STRING  : '"' ~[\r\n]* '"';
WS           : [ \t]+ -> skip ;
