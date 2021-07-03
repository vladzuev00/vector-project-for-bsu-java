package by.bsu.zuevvlad.secondlab.vector.validator;

import by.bsu.zuevvlad.secondlab.vector.Vector;
import by.bsu.zuevvlad.secondlab.vector.VectorTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public final class VectorValidatorTest
{
    @Test
    public final void capacityShouldBeValid()
    {
        final int researchCapacity = VectorValidatorTest.generateValidCapacity();
        final VectorValidator vectorValidator = new VectorValidator();
        Assert.assertTrue(vectorValidator.isValidCapacity(researchCapacity));
    }

    private static int generateValidCapacity()
    {
        return (int)(Math.random() * (VectorValidator.MAXIMAL_ALLOWABLE_VALUE_OF_CAPACITY
                - VectorValidator.MINIMAL_ALLOWABLE_VALUE_OF_CAPACITY))
                + VectorValidator.MINIMAL_ALLOWABLE_VALUE_OF_CAPACITY;
    }

    @Test
    public final void capacityShouldNotBeValid()
    {
        final int researchCapacity = VectorValidator.MINIMAL_ALLOWABLE_VALUE_OF_CAPACITY - 1;
        final VectorValidator vectorValidator = new VectorValidator();
        Assert.assertFalse(vectorValidator.isValidCapacity(researchCapacity));
    }

    @Test
    public final void indexToAddNumberShouldBeValid()
    {
        final int researchIndex = VectorValidatorTest.AMOUNT_OF_NUMBER_IN_READY_VECTOR;
        final VectorValidator vectorValidator = new VectorValidator();
        Assert.assertTrue(vectorValidator.isValidIndexToAddNumber(researchIndex, VectorValidatorTest.READY_VECTOR));
    }

    private static final int AMOUNT_OF_NUMBER_IN_READY_VECTOR = 50;
    private static final Vector<Integer> READY_VECTOR = new Vector<Integer>(
            VectorValidatorTest.AMOUNT_OF_NUMBER_IN_READY_VECTOR);
    private static final int MINIMAL_BORDER_OF_GENERATION_NUMBER = -10;
    private static final int MAXIMAL_BORDER_OF_GENERATION_NUMBER = 10;

    static
    {
        int counterOfAlreadyGeneratedNumbers = 0;
        int generatedNumber;
        while(counterOfAlreadyGeneratedNumbers != VectorValidatorTest.AMOUNT_OF_NUMBER_IN_READY_VECTOR)
        {
            generatedNumber = VectorValidatorTest.generateInteger();
            VectorValidatorTest.READY_VECTOR.addNumber(generatedNumber);
            counterOfAlreadyGeneratedNumbers++;
        }
    }

    private static int generateInteger()
    {
        return    (int)(Math.random() * (VectorValidatorTest.MAXIMAL_BORDER_OF_GENERATION_NUMBER
                - VectorValidatorTest.MINIMAL_BORDER_OF_GENERATION_NUMBER))
                + VectorValidatorTest.MINIMAL_BORDER_OF_GENERATION_NUMBER;
    }

    @Test
    public final void indexToAddNumberShouldNotBeValid()
    {
        final int researchIndex = VectorValidatorTest.AMOUNT_OF_NUMBER_IN_READY_VECTOR + 1;
        final VectorValidator vectorValidator = new VectorValidator();
        Assert.assertFalse(vectorValidator.isValidIndexToAddNumber(researchIndex, VectorValidatorTest.READY_VECTOR));
    }

    @Test
    public final void indexShouldBeValid()
    {
        final int researchIndex = VectorValidatorTest.AMOUNT_OF_NUMBER_IN_READY_VECTOR - 1;
        final VectorValidator vectorValidator = new VectorValidator();
        Assert.assertTrue(vectorValidator.isValidIndex(researchIndex, VectorValidatorTest.READY_VECTOR));
    }

    @Test
    public final void indexShouldNotBeValid()
    {
        final int researchIndex = VectorValidatorTest.AMOUNT_OF_NUMBER_IN_READY_VECTOR;
        final VectorValidator vectorValidator = new VectorValidator();
        Assert.assertFalse(vectorValidator.isValidIndex(researchIndex, VectorValidatorTest.READY_VECTOR));
    }
}
