import java.util.ArrayList;

public class Test extends GolzinhoBaseListener {
	StringBuilder builder = new StringBuilder();
	ArrayList<String> channels = new ArrayList<String>();

	@Override
	public void enterAssignChan(GolzinhoParser.AssignChanContext ctx) {
		String chan = ctx.ID().getText();
		builder.append("(ν " + chan + chan + "'" + ")");
		channels.add(chan);
	}

	@Override
	public void exitAssignChan(GolzinhoParser.AssignChanContext ctx) {
		// String chan = ctx.ID().getText();
		// builder.append(
		// 	" | " + chan + chan + "'" + ": ∅" +
		// 	" | " + chan + "'" + chan + ": ∅"
		// );
	}

	@Override
	public void exitStmtList(GolzinhoParser.StmtListContext ctx) {
		for (String chan : channels) {
			builder.append(
				" | " + chan + chan + "'" + ": ∅" +
				" | " + chan + "'" + chan + ": ∅"
			);
		}
	}

	@Override
	public void enterFunc(GolzinhoParser.FuncContext ctx) {
		ArrayList<String> paramsBasic = new ArrayList<String>();
		ArrayList<String> paramsChan = new ArrayList<String>();
		GolzinhoParser.ParamListContext list = ctx.paramList();
		if (list != null) {
			for (GolzinhoParser.ParamContext p : list.param()) {
				if (p.in() != null || p.out() != null) {
					paramsChan.add(p.ID().getText());
					continue;
				}

				paramsBasic.add(p.ID().getText());
			}
		}

		String funcName = ctx.ID().getText();

		if (funcName.equals("main")) {
			return;
		}

		System.out.print(
			"def " + funcName + "(" + printParams(paramsBasic, paramsChan) + ") = "
		);
	}

	@Override
	public void exitFunc(GolzinhoParser.FuncContext ctx) {
		System.out.print(builder.toString());
		builder.setLength(0);
		channels.clear();

		if (ctx.ID().getText().equals("main")) {
			return;
		}

		System.out.println(" in ");
	}

	private String printParams(ArrayList<String> paramsBasic, ArrayList<String> paramsChan) {
		if (paramsBasic.isEmpty() && paramsChan.isEmpty()) return "";

		String paramList = "";
		String tmp = paramsBasic.getFirst();
		if (tmp == null) {
			paramList += "∅";
		} else {
			paramList += tmp;
		}
		for (int i = 1; i < paramsBasic.size(); i++) {
			paramList += " " + paramsBasic.get(i);
		}
		paramList += "; ";

		tmp = paramsChan.getFirst();
		if (tmp == null) {
			paramList += "∅";
		} else {
			paramList += tmp;
		}
		for (int i = 1; i < paramsChan.size(); i++) {
			paramList += " " + paramsChan.get(i);
		}

		return paramList;
	}
}
