package by.bsu.zuevvlad.secondlab.vector;

import by.bsu.zuevvlad.secondlab.vector.validator.VectorValidator;
import by.bsu.zuevvlad.secondlab.vector.exception.VectorCreatingException;
import by.bsu.zuevvlad.secondlab.vector.exception.VectorModificationException;
import by.bsu.zuevvlad.secondlab.vector.exception.VectorSearchingException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;

public final class VectorTest
{
    @Test
    public final void vectorShouldBeCreatedByDefault()
    {
        final Vector<Integer> createdVector = new Vector<Integer>();
        final int expectedAmountOfNumbers = 0;
        final int actualAmountOfNumbers = createdVector.getAmountOfNumbers();
        Assert.assertEquals(actualAmountOfNumbers, expectedAmountOfNumbers);
    }

    @Test
    public final void vectorShouldBeCreatedWithGivenInitialCapacity()
    {
        final int initialCapacityOfCreatedVector = 25;
        final Vector<Integer> createdVector = new Vector<Integer>(initialCapacityOfCreatedVector);
        final int expectedAmountOfNumbers = 0;
        final int actualAmountOfNumbers = createdVector.getAmountOfNumbers();
        Assert.assertEquals(actualAmountOfNumbers, expectedAmountOfNumbers);
    }

    @Test(expectedExceptions = VectorCreatingException.class)
    public final void vectorShouldNotBeCreatedWithGivenInitialCapacity()
    {
        final int initialCapacityOfCreatedVector = VectorValidator.MINIMAL_ALLOWABLE_VALUE_OF_CAPACITY - 1;
        new Vector<Integer>(initialCapacityOfCreatedVector);
    }

    @Test
    public final void vectorShouldBeCreatedByAlreadyCreatedVector()
    {
        final Vector<Integer> createdVector = new Vector<Integer>(VectorTest.READY_VECTOR);
        Assert.assertEquals(createdVector, VectorTest.READY_VECTOR);
    }

    private static final int AMOUNT_OF_NUMBER_IN_READY_VECTOR = 50;
    private static final Vector<Integer> READY_VECTOR = new Vector<Integer>(VectorTest.AMOUNT_OF_NUMBER_IN_READY_VECTOR);
    private static final int MINIMAL_BORDER_OF_GENERATION_NUMBER = -10;
    private static final int MAXIMAL_BORDER_OF_GENERATION_NUMBER = 10;

    static
    {
        int counterOfAlreadyGeneratedNumbers = 0;
        int generatedNumber;
        while(counterOfAlreadyGeneratedNumbers != VectorTest.AMOUNT_OF_NUMBER_IN_READY_VECTOR)
        {
            generatedNumber = VectorTest.generateInteger();
            VectorTest.READY_VECTOR.addNumber(generatedNumber);
            counterOfAlreadyGeneratedNumbers++;
        }
    }

    private static int generateInteger()
    {
        return    (int)(Math.random() * (VectorTest.MAXIMAL_BORDER_OF_GENERATION_NUMBER
                - VectorTest.MINIMAL_BORDER_OF_GENERATION_NUMBER)) + VectorTest.MINIMAL_BORDER_OF_GENERATION_NUMBER;
    }

    @Test
    public final void amountOfNumbersShouldBeGot()
    {
        final int expectedAmountOfNumbers = VectorTest.AMOUNT_OF_NUMBER_IN_READY_VECTOR;
        final int actualAmountOfNumbers = VectorTest.READY_VECTOR.getAmountOfNumbers();
        Assert.assertEquals(actualAmountOfNumbers, expectedAmountOfNumbers);
    }

    @Test
    public final void capacityShouldBeFound()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int expectedCapacity = Vector.DEFAULT_INITIAL_CAPACITY;
        final int actualCapacity = vector.findCapacity();
        Assert.assertEquals(actualCapacity, expectedCapacity);
    }

    @Test
    public final void numberShouldBeAdded()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int addedNumber = VectorTest.generateInteger();
        vector.addNumber(addedNumber);
        final int expectedAmountOfNumbers = 1;
        final int actualAmountOfNumbers = vector.getAmountOfNumbers();
        Assert.assertEquals(actualAmountOfNumbers, expectedAmountOfNumbers);
    }

    @Test
    public final void numberShouldBeAddedWithIncreasingCapacity()
    {
        final int initialCapacityOfVector = 5;
        final Vector<Integer> vector = new Vector<Integer>(initialCapacityOfVector);
        final int amountOfAddedNumbers = initialCapacityOfVector + 1;
        int generatedNumber;
        for(int i = 0; i < amountOfAddedNumbers; i++)
        {
            generatedNumber = VectorTest.generateInteger();
            vector.addNumber(generatedNumber);
        }
        final int expectedAmountOfNumbers = amountOfAddedNumbers;
        final int actualAmountOfNumbers = vector.getAmountOfNumbers();
        Assert.assertEquals(actualAmountOfNumbers, expectedAmountOfNumbers);
    }

    @Test
    public final void numberShouldBeAddedWithGivenIndex()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int amountOfAddedNumbers = 10;
        int generatedNumber;
        for(int i = 0; i < amountOfAddedNumbers; i++)
        {
            generatedNumber = VectorTest.generateInteger();
            vector.addNumber(generatedNumber);
        }
        final int indexOfAddedNumber = amountOfAddedNumbers / 2;
        final int addedNumber = VectorTest.MAXIMAL_BORDER_OF_GENERATION_NUMBER + 1;
        vector.addNumber(indexOfAddedNumber, addedNumber);
        final int expectedNumberInGivenIndex = addedNumber;
        final int actualNumberInGivenIndex = vector.findNumber(indexOfAddedNumber);
        final int expectedAmountOfNumbers = amountOfAddedNumbers + 1;
        final int actualAmountOfNumbers = vector.getAmountOfNumbers();
        Assert.assertTrue(actualAmountOfNumbers == expectedAmountOfNumbers
                && actualNumberInGivenIndex == expectedNumberInGivenIndex);
    }

    @Test(expectedExceptions = VectorModificationException.class)
    public final void numberShouldNotBeAddedWithGivenIndex()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int amountOfAddedNumbers = 10;
        int generatedNumber;
        for(int i = 0; i < amountOfAddedNumbers; i++)
        {
            generatedNumber = VectorTest.generateInteger();
            vector.addNumber(generatedNumber);
        }
        final int indexOfAddedNumber = amountOfAddedNumbers + 1;
        final int addedNumber = 1;
        vector.addNumber(indexOfAddedNumber, addedNumber);
    }

    @Test
    public final void numbersOfFirstVectorShouldBeAddedInSecondVector()
    {
        final int amountOfAddedNumbersInSecondVector = 10;
        final Vector<Integer> secondVector = new Vector<Integer>(amountOfAddedNumbersInSecondVector);
        int generatedNumber;
        for(int i = 0; i < amountOfAddedNumbersInSecondVector; i++)
        {
            generatedNumber = VectorTest.generateInteger();
            secondVector.addNumber(generatedNumber);
        }
        final Vector<Integer> firstVector = VectorTest.READY_VECTOR;
        secondVector.addNumbersOfVector(firstVector);
        final int expectedAmountOfNumbersInSecondVector = amountOfAddedNumbersInSecondVector
                + firstVector.getAmountOfNumbers();
        final int actualAmountOfNumbersInSecondVector = secondVector.getAmountOfNumbers();
        Assert.assertEquals(actualAmountOfNumbersInSecondVector, expectedAmountOfNumbersInSecondVector);
    }

    @Test
    public final void numbersOfFirstVectorShouldBeAddedInSecondVectorWithGivenIndexToStartAdding()
    {
        final int amountOfAddedNumbersInSecondVector = 10;
        final Vector<Integer> secondVector = new Vector<Integer>(amountOfAddedNumbersInSecondVector);
        int generatedNumber;
        for(int i = 0; i < amountOfAddedNumbersInSecondVector; i++)
        {
            generatedNumber = VectorTest.generateInteger();
            secondVector.addNumber(generatedNumber);
        }
        final Vector<Integer> firstVector = VectorTest.READY_VECTOR;
        final int indexToStartAdding = amountOfAddedNumbersInSecondVector / 2;
        secondVector.addNumbersOfVector(indexToStartAdding, firstVector);
        final int expectedAmountOfNumbersOfSecondVector = amountOfAddedNumbersInSecondVector
                + firstVector.getAmountOfNumbers();
        final int actualAmountOfNumbersOfSecondVector = secondVector.getAmountOfNumbers();
        Assert.assertEquals(actualAmountOfNumbersOfSecondVector, expectedAmountOfNumbersOfSecondVector);
    }

    @Test
    public final void vectorShouldBeEmpty()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        Assert.assertTrue(vector.isEmpty());
    }

    @Test
    public final void vectorShouldNotBeEmpty()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int addedNumber = VectorTest.generateInteger();
        vector.addNumber(addedNumber);
        Assert.assertFalse(vector.isEmpty());
    }

    @Test
    public final void numberShouldBeContainedInVector()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int amountOfAddedNumbers = 10;
        int generatedNumber = 0;
        for(int i = 0; i < amountOfAddedNumbers; i++)
        {
            generatedNumber = VectorTest.generateInteger();
            vector.addNumber(generatedNumber);
        }
        final int researchNumber = generatedNumber;
        final boolean expectedResultOfCheckingContaining = true;
        final boolean actualResultOfCheckingContaining = vector.isNumberContain(researchNumber);
        Assert.assertEquals(actualResultOfCheckingContaining, expectedResultOfCheckingContaining);
    }

    @Test
    public final void numberShouldNotBeContainedInVector()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int amountOfAddedNumbers = 10;
        int generatedNumber = 0;
        for(int i = 0; i < amountOfAddedNumbers; i++)
        {
            generatedNumber = VectorTest.generateInteger();
            vector.addNumber(generatedNumber);
        }
        final int researchNumber = VectorTest.MAXIMAL_BORDER_OF_GENERATION_NUMBER + 1;
        final boolean expectedResultOfCheckingContaining = false;
        final boolean actualResultOfCheckingContaining = vector.isNumberContain(researchNumber);
        Assert.assertEquals(actualResultOfCheckingContaining, expectedResultOfCheckingContaining);
    }

    @Test
    public final void numberShouldBeFoundByItsIndex()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int amountOfAddedNumbers = 10;
        final int indexOfResearchNumber = amountOfAddedNumbers / 2;
        int researchNumber = VectorTest.MINIMAL_BORDER_OF_GENERATION_NUMBER - 1;
        int generatedNumber;
        for(int i = 0; i < amountOfAddedNumbers; i++)
        {
            generatedNumber = VectorTest.generateInteger();
            vector.addNumber(generatedNumber);
            if(i == indexOfResearchNumber)
            {
                researchNumber = generatedNumber;
            }
        }
        final int expectedFoundNumber = researchNumber;
        final int actualFoundNumber = vector.findNumber(indexOfResearchNumber);
        Assert.assertEquals(actualFoundNumber, expectedFoundNumber);
    }

    @Test(expectedExceptions = VectorSearchingException.class)
    public final void numberShouldNotBeFoundByItsIndex()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int amountOfAddedNumbers = 10;
        int generatedNumber;
        for(int i = 0; i < amountOfAddedNumbers; i++)
        {
            generatedNumber = VectorTest.generateInteger();
            vector.addNumber(generatedNumber);
        }
        final int indexOfFoundNumber = amountOfAddedNumbers;
        vector.findNumber(indexOfFoundNumber);
    }

    @Test
    public final void numberShouldBeUpdated()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int amountOfAddedNumbers = 10;
        int generatedNumber;
        for(int i = 0; i < amountOfAddedNumbers; i++)
        {
            generatedNumber = VectorTest.generateInteger();
            vector.addNumber(generatedNumber);
        }
        final int indexOfUpdatedNumber = amountOfAddedNumbers / 2;
        final int newValueOfUpdatedNumber = 50;
        vector.updateNumber(indexOfUpdatedNumber, newValueOfUpdatedNumber);
        final int expectedValueOfUpdatedNumber = newValueOfUpdatedNumber;
        final int actualValueOfUpdatedNumber = vector.findNumber(indexOfUpdatedNumber);
        Assert.assertEquals(actualValueOfUpdatedNumber, expectedValueOfUpdatedNumber);
    }

    @Test(expectedExceptions = VectorModificationException.class)
    public final void numberShouldNotBeUpdated()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int amountOfAddedNumbers = 10;
        int generatedNumber;
        for(int i = 0; i < amountOfAddedNumbers; i++)
        {
            generatedNumber = VectorTest.generateInteger();
            vector.addNumber(generatedNumber);
        }
        final int indexOfUpdatedNumber = amountOfAddedNumbers;
        final int newValueOfUpdatedNumber = 50;
        vector.updateNumber(indexOfUpdatedNumber, newValueOfUpdatedNumber);
    }

    @Test
    public final void vectorShouldBeCleared()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int amountOfAddedNumbers = 10;
        int generatedNumber;
        for(int i = 0; i < amountOfAddedNumbers; i++)
        {
            generatedNumber = VectorTest.generateInteger();
            vector.addNumber(generatedNumber);
        }
        vector.clear();
        final int expectedAmountOfNumbers = 0;
        final int actualAmountOfNumbers = vector.getAmountOfNumbers();
        Assert.assertEquals(actualAmountOfNumbers, expectedAmountOfNumbers);
    }

    @Test
    public final void indexOfFirstOccurrenceOfResearchNumberShouldBeFound()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int amountOfAddedNumbers = 10;
        final int indexOfResearchNumber = amountOfAddedNumbers / 2;
        int researchNumber = VectorTest.MAXIMAL_BORDER_OF_GENERATION_NUMBER + 1;
        int generatedNumber;
        for(int i = 0; i < amountOfAddedNumbers; i++)
        {
            if(i == indexOfResearchNumber)
            {
                vector.addNumber(researchNumber);
                continue;
            }
            generatedNumber = VectorTest.generateInteger();
            vector.addNumber(generatedNumber);
        }
        vector.addNumber(researchNumber);
        final int expectedIndexOfFirstOccurrenceOfResearchNumber = indexOfResearchNumber;
        final int actualIndexOfFirstOccurrenceOfResearchNumber
                = vector.findIndexOfFirstOccurrenceOfNumber(researchNumber);
        Assert.assertEquals(actualIndexOfFirstOccurrenceOfResearchNumber,
                expectedIndexOfFirstOccurrenceOfResearchNumber);
    }

    @Test(expectedExceptions = VectorSearchingException.class)
    public final void indexOfFirstOccurrenceOfResearchNumberShouldNotBeFound()
    {
        final int researchNumber = VectorTest.MAXIMAL_BORDER_OF_GENERATION_NUMBER + 1;
        VectorTest.READY_VECTOR.findIndexOfFirstOccurrenceOfNumber(researchNumber);
    }

    @Test
    public final void indexOfLastOccurrenceOfResearchNumberShouldBeFound()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int amountOfAddedNumbers = 10;
        final int indexOfResearchNumber = amountOfAddedNumbers / 2;
        int researchNumber = VectorTest.MAXIMAL_BORDER_OF_GENERATION_NUMBER + 1;
        int generatedNumber;
        for(int i = 0; i < amountOfAddedNumbers; i++)
        {
            if(i == indexOfResearchNumber)
            {
                vector.addNumber(researchNumber);
                continue;
            }
            generatedNumber = VectorTest.generateInteger();
            vector.addNumber(generatedNumber);
        }
        vector.addNumber(researchNumber);
        final int actualIndexOfLastOccurrenceOfResearchNumber
                = vector.findIndexOfLastOccurrenceOfNumber(researchNumber);
        final int expectedIndexOfLastOccurrenceOfResearchNumber = vector.getAmountOfNumbers() - 1;
        Assert.assertEquals(actualIndexOfLastOccurrenceOfResearchNumber,
                expectedIndexOfLastOccurrenceOfResearchNumber);
    }

    @Test(expectedExceptions = VectorSearchingException.class)
    public final void indexOfLastOccurrenceOfResearchNumberShouldNotBeFound()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int amountOfAddedNumbers = 10;
        int generatedNumber;
        for(int i = 0; i < amountOfAddedNumbers; i++)
        {
            generatedNumber = VectorTest.generateInteger();
            vector.addNumber(generatedNumber);
        }
        final int researchNumber = VectorTest.MAXIMAL_BORDER_OF_GENERATION_NUMBER + 1;
        vector.findIndexOfLastOccurrenceOfNumber(researchNumber);
    }

    @Test
    public final void numberShouldBeRemovedByGivenIndex()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int amountOfAddedNumbers = 10;
        int generatedNumber;
        for(int i = 0; i < amountOfAddedNumbers; i++)
        {
            generatedNumber = VectorTest.generateInteger();
            vector.addNumber(generatedNumber);
        }
        final int indexOfRemovedNumber = amountOfAddedNumbers / 2;
        vector.removeNumber(indexOfRemovedNumber);
        final int expectedAmountOfNumbersOfVector = amountOfAddedNumbers - 1;
        final int actualAmountOfNumbersOfVector = vector.getAmountOfNumbers();
        Assert.assertEquals(actualAmountOfNumbersOfVector, expectedAmountOfNumbersOfVector);
    }

    @Test(expectedExceptions = VectorModificationException.class)
    public final void numberShouldNotBeRemovedByGivenIndex()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int amountOfAddedNumbers = 10;
        int generatedNumber;
        for(int i = 0; i < amountOfAddedNumbers; i++)
        {
            generatedNumber = VectorTest.generateInteger();
            vector.addNumber(generatedNumber);
        }
        final int indexOfRemovedNumber = amountOfAddedNumbers;
        vector.removeNumber(indexOfRemovedNumber);
    }

    @Test
    public final void vectorShouldBeTrimmedToAmountOfNumbers()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int amountOfAddedNumbers = 10;
        int generatedNumber;
        for(int i = 0; i < amountOfAddedNumbers; i++)
        {
            generatedNumber = VectorTest.generateInteger();
            vector.addNumber(generatedNumber);
        }
        vector.trimToAmountOfNumbers();
        final int expectedCapacityOfVector = amountOfAddedNumbers;
        final int actualCapacityOfVector = vector.findCapacity();
        Assert.assertEquals(actualCapacityOfVector, expectedCapacityOfVector);
    }

    @Test
    public final void vectorShouldBeIteratedByIterator()
    {
        final Iterable<Integer> iteratedVector = new Vector<Integer>(VectorTest.READY_VECTOR);
        final Iterator<Integer> iterator = iteratedVector.iterator();
        Integer numberOfIteratedVector;
        Integer numberOfReadyVector;
        int runnerIndexOfReadyVector = 0;
        boolean isFoundDifferenceBetweenNumbersOfVectors = false;
        while(iterator.hasNext())
        {
            numberOfIteratedVector = iterator.next();
            numberOfReadyVector = VectorTest.READY_VECTOR.findNumber(runnerIndexOfReadyVector);
            if(!numberOfIteratedVector.equals(numberOfReadyVector))
            {
                isFoundDifferenceBetweenNumbersOfVectors = true;
                break;
            }
            runnerIndexOfReadyVector++;
        }
        Assert.assertFalse(isFoundDifferenceBetweenNumbersOfVectors);
    }

    @Test
    public final void twoVectorsShouldBeEqual()
    {
        final Vector<Integer> firstVector = VectorTest.READY_VECTOR;
        final Vector<Integer> secondVector = new Vector<Integer>(firstVector);
        Assert.assertEquals(firstVector, secondVector);
    }

    @Test
    public final void twoVectorsShouldNotBeEqual()
    {
        final Vector<Integer> firstVector = VectorTest.READY_VECTOR;
        final Vector<Integer> secondVector = new Vector<Integer>(firstVector);
        final int indexOfUpdatedNumberOfSecondVector = secondVector.getAmountOfNumbers() / 2;
        final int newValueOfUpdatedNumberOfSecondVector = VectorTest.MAXIMAL_BORDER_OF_GENERATION_NUMBER + 1;
        secondVector.updateNumber(indexOfUpdatedNumberOfSecondVector, newValueOfUpdatedNumberOfSecondVector);
        Assert.assertNotEquals(firstVector, secondVector);
    }

    @Test
    public final void vectorShouldBeTransformedToString()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int firstNumberOfVector = 1;
        final int secondNumberOfVector = 2;
        final int[] numbersOfVector = new int[]{firstNumberOfVector, secondNumberOfVector};
        vector.addNumber(firstNumberOfVector);
        vector.addNumber(secondNumberOfVector);
        final String expectedString = Vector.class.getSimpleName() + "[numbers = "
                + Arrays.toString(numbersOfVector) + ", amountOfNumbers = " + vector.getAmountOfNumbers() + "]";
        final String actualString = vector.toString();
        Assert.assertEquals(actualString, expectedString);
    }

    @Test
    public final void hashCodeOfVectorShouldBeGot()
    {
        final Vector<Integer> vector = new Vector<Integer>();
        final int firstNumberOfVector = 1;
        final int secondNumberOfVector = 2;
        vector.addNumber(firstNumberOfVector);
        vector.addNumber(secondNumberOfVector);
        final int[] numbersOfVector = new int[vector.findCapacity()];
        numbersOfVector[0] = firstNumberOfVector;
        numbersOfVector[1] = secondNumberOfVector;
        final int expectedHashCodeOfVector = Arrays.hashCode(numbersOfVector)
                + Integer.hashCode(vector.getAmountOfNumbers());
        final int actualHashCodeOfVector = vector.hashCode();
        Assert.assertEquals(actualHashCodeOfVector, expectedHashCodeOfVector);
    }
}
