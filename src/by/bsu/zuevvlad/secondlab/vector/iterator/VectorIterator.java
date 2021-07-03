package by.bsu.zuevvlad.secondlab.vector.iterator;

import by.bsu.zuevvlad.secondlab.vector.Vector;
import by.bsu.zuevvlad.secondlab.vector.iterator.exception.VectorIterationException;

import java.util.Iterator;

public final class VectorIterator<NumberType extends Number> implements Iterator<NumberType>
{
    private final Vector<NumberType> vector;
    private int nextIndex;

    public VectorIterator(final Vector<NumberType> vector)
    {
        this.vector = vector;
        this.nextIndex = VectorIterator.INITIAL_NEXT_INDEX;
    }

    public static final int INITIAL_NEXT_INDEX = 0;

    public final Vector<NumberType> getVector()
    {
        return this.vector;
    }

    public final int getNextIndex()
    {
        return this.nextIndex;
    }

    @Override
    public final boolean hasNext()
    {
        return this.nextIndex < this.vector.getAmountOfNumbers();
    }

    @Override
    public final NumberType next()
    {
        if (!this.hasNext())
        {
            throw new VectorIterationException("Impossible to find number of vector '" + this.vector + "'"
                    + " with index '" + this.nextIndex + "'.");
        }
        final NumberType nextNumber = this.vector.findNumber(this.nextIndex);
        this.nextIndex++;
        return nextNumber;
    }
}
