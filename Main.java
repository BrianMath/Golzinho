import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
	public static void main(String[] args) throws Exception {
		String inputFile = null;

		if (args.length > 0) {
			inputFile = args[0];
		}

		InputStream is = System.in;
		if (inputFile != null) {
			is = new FileInputStream(inputFile);
		}

		CharStream input = CharStreams.fromStream(is);
		GolzinhoLexer lexer = new GolzinhoLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		GolzinhoParser parser = new GolzinhoParser(tokens);
		ParseTree tree = parser.program(); // begin parsing at init rule

		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(new Test(), tree);
		System.out.println();
	}
}
