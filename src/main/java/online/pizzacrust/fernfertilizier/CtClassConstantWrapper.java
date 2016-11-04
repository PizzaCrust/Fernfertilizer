package online.pizzacrust.fernfertilizier;

import javassist.CtClass;

public class CtClassConstantWrapper extends ConstantProvider.BaseConstantProvider {

    private final CtClass ctClass;

    public CtClassConstantWrapper(CtClass ctClass) {
        this.ctClass = ctClass;
    }

    public static CtClassConstantWrapper wrap(CtClass ctClass) {
        return new CtClassConstantWrapper(ctClass);
    }

    @Override
    public String[] stringConstants() {
        return ConstantUtil.getStrings(ctClass);
    }

    @Override
    public Integer[] integerConstants() {
        return ConstantUtil.getIntegers(ctClass);
    }

}
