package eu.mihosoft.vrl.instrumentation.composites;

import org.codehaus.groovy.ast.stmt.ContinueStatement;
import org.codehaus.groovy.control.SourceUnit;

import eu.mihosoft.vrl.instrumentation.transform.TransformContext;
import eu.mihosoft.vrl.lang.model.CodeLineColumnMapper;
import eu.mihosoft.vrl.lang.model.ContinueInvocation;
import eu.mihosoft.vrl.lang.model.ControlFlowScope;
import eu.mihosoft.vrl.lang.model.VisualCodeBuilder;

public class ContinuePart
		extends
		AbstractCodeBuilderPart<ContinueStatement, ContinueInvocation, ControlFlowScope> {

	public ContinuePart(SourceUnit sourceUnit, VisualCodeBuilder builder,
			CodeLineColumnMapper mapper) {
		super(sourceUnit, builder, mapper);
	}

	@Override
	public ContinueInvocation transform(ContinueStatement obj,
			ControlFlowScope parent, TransformContext context) {
		return builder.invokeContinue(parent);
	}

	@Override
	public Class<ContinueStatement> getAcceptedType() {
		return ContinueStatement.class;
	}

	@Override
	public Class<ControlFlowScope> getParentType() {
		return ControlFlowScope.class;
	}
}
