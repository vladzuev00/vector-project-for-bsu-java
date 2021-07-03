package by.bsu.zuevvlad.secondlab.vector.validator;

import by.bsu.zuevvlad.secondlab.vector.Vector;

public final class VectorValidator
{
    public final boolean isValidCapacity(final int researchCapacity)
    {
        return     VectorValidator.MINIMAL_ALLOWABLE_VALUE_OF_CAPACITY <= researchCapacity
                && researchCapacity <= VectorValidator.MAXIMAL_ALLOWABLE_VALUE_OF_CAPACITY;
    }

    public static final int MINIMAL_ALLOWABLE_VALUE_OF_CAPACITY = 1;
    public static final int MAXIMAL_ALLOWABLE_VALUE_OF_CAPACITY = 99999999;

    public final boolean isValidIndexToAddNumber(final int researchIndex, final Vector<?> vector)
    {
        return 0 <= researchIndex && researchIndex <= vector.getAmountOfNumbers();
    }

    public final boolean isValidIndex(final int researchIndex, final Vector<?> vector)
    {
        return 0 <= researchIndex && researchIndex <= vector.getAmountOfNumbers() - 1;
    }
}
