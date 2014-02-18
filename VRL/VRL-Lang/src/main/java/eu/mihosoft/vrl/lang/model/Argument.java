/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.vrl.lang.model;

import java.util.Optional;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public final class Argument implements IArgument {

    private final ArgumentType argType;
    private final Variable variable;
    private final Object constant;
    private final Invocation invocation;
    private final IType constType;

    public static final IArgument NULL = new Argument(ArgumentType.NULL, null, null, null, null);

    private Argument(ArgumentType argType, Variable variable, Object constant, IType constType, Invocation invocation) {
        this.argType = argType;
        this.variable = variable;
        this.constant = constant;
        this.constType = constType;
        this.invocation = invocation;
    }

    public static IArgument newConstArg(IType type, Object constant) {
        IArgument result = new Argument(ArgumentType.CONSTANT, null, constant, type, null);

        return result;
    }

    public static IArgument newVarArg(Variable v) {
        IArgument result = new Argument(ArgumentType.VARIABLE, v, null, null, null);

        return result;
    }

    public static IArgument newInvArg(Invocation i) {
        IArgument result = new Argument(ArgumentType.INVOCATION, null, null, null, i);

        return result;
    }

    public static IArgument newNullArg() {
        return NULL;
    }

    @Override
    public ArgumentType getArgType() {
        return this.argType;
    }

    @Override
    public Optional<Variable> getVariable() {
        return Optional.ofNullable(variable);
    }

    @Override
    public Optional<Invocation> getInvocation() {
        return Optional.ofNullable(invocation);
    }

    @Override
    public Optional<Object> getConstant() {
        return Optional.ofNullable(constant);
    }

    @Override
    public IType getType() {
        if (getArgType() == ArgumentType.CONSTANT) {
            return constType;
        } else if (getArgType() == ArgumentType.VARIABLE) {
            return getVariable().get().getType();
        } else if (getArgType() == ArgumentType.INVOCATION) {
            return getInvocation().get().getReturnType();
        }
        return Type.VOID;
    }

}
