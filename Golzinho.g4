grammar Golzinho;
import LexerRules;

program    : package func* ;

package    : PACKAGE ID ;

func       : FUNC ID L_PAREN param? R_PAREN L_BRACE stmtList R_BRACE;

param      : ID (in | out)? TYPE (COMMA param)*;

in		   : CHAN '<-' ;

out        : '<-' CHAN ;

stmtList   : (stmt SEMICOLON?)*;

exprList   : expression (COMMA expression)*;

stmt       : assign
		   | goroutine
		   | if
		   | return
		   ;

assign     : assignChan
		   | ID (DECLARE_ASSIGN | RECEIVE | ASSIGN) expression
		   ;

assignChan : ID DECLARE_ASSIGN channel;

expression : equality;

equality   : comparison ((EQUAL | NOT_EQUAL) comparison)*;

comparison : term ((GREATER | GREATER_EQUAL | LESS | LESS_EQUAL) term)*;

term       : factor ((PLUS | MINUS) factor)*;

factor     : unary ((MUL | DIV) unary)*;

unary      : (LOGICAL_NOT | MINUS | RECEIVE) unary
		   | primary
		   ;

primary    : INT
		   | STRING
		   | ID
		   | L_PAREN expression R_PAREN
		   ;

if         : IF L_PAREN expression R_PAREN L_BRACE stmtList R_BRACE (else | elseif)?
		   | IF expression L_BRACE stmtList R_BRACE (else | elseif)?
		   ;

elseif     : ELSE if ;

else       : ELSE L_BRACE stmtList R_BRACE ;

return     : RETURN expression? SEMICOLON? ;

channel    : MAKE L_PAREN CHAN TYPE (COMMA INT)? R_PAREN ;

goroutine  : GO ID L_PAREN exprList? R_PAREN ;
