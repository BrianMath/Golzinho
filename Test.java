import java.util.ArrayList;

public class Test extends GolzinhoBaseListener {
	@Override
	public void enterAssignChan(GolzinhoParser.AssignChanContext ctx) {
		String chan = ctx.ID().getText();
		chan = "(ν " + chan + chan + "'" + ")";
		System.out.print(chan);
	}

	@Override
	public void enterFunc(GolzinhoParser.FuncContext ctx) {
		ArrayList<String> params = new ArrayList<String>();
		GolzinhoParser.ParamContext first = ctx.param();
		if (first != null) {
			params.add(first.ID().getText());
			for (GolzinhoParser.ParamContext p : first.param()) {
				params.add(p.ID().getText());
			}
		}

		String funcName = ctx.ID().getText();

		if (funcName.equals("main")) {
			return;
		}

		System.out.println(
			"def " + funcName + "(" + printParams(params) + ") = ... in"
		);
	}

	private String printParams(ArrayList<String> params) {
		if (params.size() == 0) return "";

		String paramList = params.getFirst();
		for (int i = 1; i < params.size(); i++) {
			paramList += " " + params.get(i);
		}
		return paramList;
	}
}
