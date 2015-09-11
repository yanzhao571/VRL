package eu.mihosoft.vrl.instrumentation.composites;

import org.codehaus.groovy.ast.expr.PostfixExpression;
import org.codehaus.groovy.control.SourceUnit;

import eu.mihosoft.vrl.instrumentation.transform.TransformContext;
import eu.mihosoft.vrl.lang.model.Argument;
import eu.mihosoft.vrl.lang.model.BinaryOperatorInvocation;
import eu.mihosoft.vrl.lang.model.BinaryOperatorInvocationImpl;
import eu.mihosoft.vrl.lang.model.CodeEntity;
import eu.mihosoft.vrl.lang.model.CodeLineColumnMapper;
import eu.mihosoft.vrl.lang.model.ControlFlowScope;
import eu.mihosoft.vrl.lang.model.VisualCodeBuilder;

public class PostFixExpressionPart
		extends
		AbstractCodeBuilderPart<PostfixExpression, CodeEntity, ControlFlowScope> {

	public PostFixExpressionPart(SourceUnit sourceUnit,
			VisualCodeBuilder builder, CodeLineColumnMapper mapper) {
		super(sourceUnit, builder, mapper);
	}

	@Override
	public CodeEntity transform(PostfixExpression obj, ControlFlowScope parent,
			TransformContext context) {

		BinaryOperatorInvocation invocation = new BinaryOperatorInvocationImpl(
				parent, convertToArgument("PostfixExpression.argument",
						obj.getExpression(), context), Argument.NULL,
				convertPostfixOperator(obj.getOperation().getText()));

		return invocation;
	}

	@Override
	public Class<PostfixExpression> getAcceptedType() {

		return PostfixExpression.class;
	}

	@Override
	public Class<ControlFlowScope> getParentType() {
		return ControlFlowScope.class;
	}
}
