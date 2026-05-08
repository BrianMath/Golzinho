grammar Golzinho;
import LexerRules;

program    : package func* ;

package    : PACKAGE ID ;

func       : FUNC ID L_PAREN param? R_PAREN L_BRACE stmtList R_BRACE;

param      : ID (in | out)? TYPE (COMMA param)*;

in		   : CHAN '<-' ;

out        : '<-' CHAN ;

stmtList   : (stmt SEMICOLON?)*;

stmt       : assign
		   | goroutine
		   ;

assign     : ID DECLARE_ASSIGN channel
		   | ID (DECLARE_ASSIGN | RECEIVE) expression
		   ;

exprList   : expression (COMMA expression)*;

expression : equality;

equality   : comparison ((EQUAL | NOT_EQUAL) comparison)*;

comparison : term ((GREATER | GREATER_EQUAL | LESS | LESS_EQUAL) term)*;

term       : factor ((PLUS | LESS) factor)*;

factor     : unary ((MUL | DIV) unary)*;

unary      : (LOGICAL_NOT | MINUS) unary
		   | primary
		   ;

primary    : INT
		   | STRING
		   | ID
		   | L_PAREN expression R_PAREN
		   ;

channel    : MAKE L_PAREN CHAN (COMMA INT)? TYPE R_PAREN ;

goroutine  : GO ID L_PAREN exprList? R_PAREN ;
