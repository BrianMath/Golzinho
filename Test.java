public class Test extends GolzinhoBaseListener {
	@Override
	public void enterPackage(GolzinhoParser.PackageContext ctx) {
		System.out.println(ctx.ID().getText());
	}

	@Override
	public void enterAssignChan(GolzinhoParser.AssignChanContext ctx) {
		String chan = ctx.ID().getText();
		chan = "(ν " + chan + chan + "'" + ")";
		System.out.println(chan);
	}
}
