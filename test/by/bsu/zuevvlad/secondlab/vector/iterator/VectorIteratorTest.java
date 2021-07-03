package by.bsu.zuevvlad.secondlab.vector.iterator;

import by.bsu.zuevvlad.secondlab.vector.Vector;
import by.bsu.zuevvlad.secondlab.vector.iterator.exception.VectorIterationException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;

public final class VectorIteratorTest
{
    @Test
    public final void iteratorShouldBeCreated()
    {
        final VectorIterator<Integer> vectorIterator = new VectorIterator<Integer>(VectorIteratorTest.READY_VECTOR);
        final Vector<Integer> actualIteratedVector = vectorIterator.getVector();
        final Vector<Integer> expectedIteratedVector = VectorIteratorTest.READY_VECTOR;
        final int actualNextIndex = vectorIterator.getNextIndex();
        final int expectedNextIndex = VectorIterator.INITIAL_NEXT_INDEX;
        Assert.assertTrue(actualIteratedVector == expectedIteratedVector
                && actualNextIndex == expectedNextIndex);
    }

    private static final int AMOUNT_OF_NUMBER_IN_READY_VECTOR = 50;
    private static final Vector<Integer> READY_VECTOR = new Vector<Integer>(
            VectorIteratorTest.AMOUNT_OF_NUMBER_IN_READY_VECTOR);
    private static final int MINIMAL_BORDER_OF_GENERATION_NUMBER = -10;
    private static final int MAXIMAL_BORDER_OF_GENERATION_NUMBER = 10;

    static
    {
        int counterOfAlreadyGeneratedNumbers = 0;
        int generatedNumber;
        while(counterOfAlreadyGeneratedNumbers != VectorIteratorTest.AMOUNT_OF_NUMBER_IN_READY_VECTOR)
        {
            generatedNumber = VectorIteratorTest.generateInteger();
            VectorIteratorTest.READY_VECTOR.addNumber(generatedNumber);
            counterOfAlreadyGeneratedNumbers++;
        }
    }

    private static int generateInteger()
    {
        return    (int)(Math.random() * (VectorIteratorTest.MAXIMAL_BORDER_OF_GENERATION_NUMBER
                - VectorIteratorTest.MINIMAL_BORDER_OF_GENERATION_NUMBER))
                + VectorIteratorTest.MINIMAL_BORDER_OF_GENERATION_NUMBER;
    }

    @Test
    public final void iteratedVectorShouldBeGot()
    {
        final VectorIterator<Integer> vectorIterator = new VectorIterator<Integer>(VectorIteratorTest.READY_VECTOR);
        final Vector<Integer> actualIteratedVector = vectorIterator.getVector();
        final Vector<Integer> expectedIteratedVector = VectorIteratorTest.READY_VECTOR;
        Assert.assertSame(actualIteratedVector, expectedIteratedVector);
    }

    @Test
    public final void nextIndexShouldBeGot()
    {
        final VectorIterator<Integer> vectorIterator = new VectorIterator<Integer>(VectorIteratorTest.READY_VECTOR);
        final int actualNextIndex = vectorIterator.getNextIndex();
        final int expectedNextIndex = VectorIterator.INITIAL_NEXT_INDEX;
        Assert.assertEquals(actualNextIndex, expectedNextIndex);
    }

    @Test
    public final void iteratorShouldBeAbleToFindNextNumber()
    {
        final Iterator<Integer> iterator = new VectorIterator<Integer>(VectorIteratorTest.READY_VECTOR);
        final boolean isIteratorAbleToFindNextNumber = iterator.hasNext();
        Assert.assertTrue(isIteratorAbleToFindNextNumber);
    }

    @Test
    public final void iteratorShouldNotBeAbleToFindNextNumber()
    {
        final Iterator<Integer> iterator = new VectorIterator<Integer>(VectorIteratorTest.READY_VECTOR);
        while(iterator.hasNext())
        {
            iterator.next();
        }
        final boolean isIteratorAbleToFindNextNumber = iterator.hasNext();
        Assert.assertFalse(isIteratorAbleToFindNextNumber);
    }

    @Test
    public final void iteratorShouldFindNextNumber()
    {
        final Iterator<Integer> iterator = new VectorIterator<Integer>(VectorIteratorTest.READY_VECTOR);
        final Integer expectedFoundNextNumber = VectorIteratorTest.READY_VECTOR.findNumber(0);
        final Integer actualFoundNextNumber = iterator.next();
        Assert.assertSame(actualFoundNextNumber, expectedFoundNextNumber);
    }

    @Test(expectedExceptions = VectorIterationException.class)
    public final void iteratorShouldNotFindNextNumber()
    {
        final Iterator<Integer> iterator = new VectorIterator<Integer>(VectorIteratorTest.READY_VECTOR);
        while(iterator.hasNext())
        {
            iterator.next();
        }
        iterator.next();
    }
}
