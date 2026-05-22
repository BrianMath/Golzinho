import java.io.IOException;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
	public static void main(String[] args) throws Exception {
		// create a CharStream that reads from standard input
		CharStream input = CharStreams.fromStream(System.in);

		// create a lexer that feeds off of input CharStream
		GolzinhoLexer lexer = new GolzinhoLexer(input);

		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		// create a parser that feeds off the tokens buffer
		GolzinhoParser parser = new GolzinhoParser(tokens);
		ParseTree tree = parser.program(); // begin parsing at init rule

		// Create a generic parse tree walker that can trigger callbacks
		ParseTreeWalker walker = new ParseTreeWalker();
		// Walk the tree created during the parse, trigger callbacks
		walker.walk(new Test(), tree);
		System.out.println();
	}
}
