package by.bsu.zuevvlad.secondlab.vector;

import by.bsu.zuevvlad.secondlab.vector.iterator.VectorIterator;
import by.bsu.zuevvlad.secondlab.vector.validator.VectorValidator;
import by.bsu.zuevvlad.secondlab.vector.exception.VectorCreatingException;
import by.bsu.zuevvlad.secondlab.vector.exception.VectorModificationException;
import by.bsu.zuevvlad.secondlab.vector.exception.VectorSearchingException;

import java.io.Serializable;
import java.util.Arrays;

public final class Vector<NumberType extends Number> implements Iterable<NumberType>, Serializable
{
    private static final long serialVersionUID = 1L;

    private Number[] numbers;
    private int amountOfNumbers;

    public Vector()
    {
        super();
        this.numbers = new Number[Vector.DEFAULT_INITIAL_CAPACITY];
        this.amountOfNumbers = 0;
    }

    public static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Vector(final int initialCapacity)
    {
        super();
        if(!Vector.VECTOR_VALIDATOR.isValidCapacity(initialCapacity))
        {
            throw new VectorCreatingException("Impossible to create object of class '" + this.getClass().getName()
                    + "' with given not valid initial capacity: " + initialCapacity + ".");
        }
        this.numbers = new Number[initialCapacity];
        this.amountOfNumbers = 0;
    }

    private static final VectorValidator VECTOR_VALIDATOR = new VectorValidator();

    public Vector(final Vector<NumberType> other)
    {
        super();
        this.amountOfNumbers = other.amountOfNumbers;
        this.numbers = Arrays.copyOf(other.numbers, other.amountOfNumbers);
    }

    public final int getAmountOfNumbers()
    {
        return this.amountOfNumbers;
    }

    public final int findCapacity()
    {
        return this.numbers.length;
    }

    public final void addNumber(final NumberType addedNumber)
    {
        if(this.amountOfNumbers == this.numbers.length)
        {
            this.increaseCapacity();
        }
        final int indexOfAddedNumber = this.amountOfNumbers;
        this.numbers[indexOfAddedNumber] = addedNumber;
        this.amountOfNumbers++;
    }

    private final void increaseCapacity()
    {
        final int newCapacity = (int)(this.numbers.length * (1 + Vector.COEFFICIENT_OF_INCREASING_CAPACITY));
        this.changeCapacity(newCapacity);
    }

    private void changeCapacity(final int newCapacity)
    {
        final Number[] numbers = this.numbers;
        this.numbers = Arrays.<Number>copyOf(numbers, newCapacity);
    }

    private static final double COEFFICIENT_OF_INCREASING_CAPACITY = 0.75;

    public final void addNumber(final int indexOfAddedElement, final NumberType addedNumber)
    {
        if(!Vector.VECTOR_VALIDATOR.isValidIndexToAddNumber(indexOfAddedElement, this))
        {
            throw new VectorModificationException("Impossible to add new number in vector with given "
                    + "not valid index: " + indexOfAddedElement + ".");
        }
        if(this.amountOfNumbers == this.numbers.length)
        {
            this.increaseCapacity();
        }
        for(int i = this.amountOfNumbers - 1; i >= indexOfAddedElement; i--)
        {
            this.numbers[i + 1] = this.numbers[i];
        }
        this.numbers[indexOfAddedElement] = addedNumber;
        this.amountOfNumbers++;
    }

    public final void addNumbersOfVector(final Vector<NumberType> vector)
    {
        if(vector.isEmpty())
        {
            return;
        }
        if(this.numbers.length < this.amountOfNumbers + vector.amountOfNumbers)
        {
            final int newCapacity = this.amountOfNumbers + vector.amountOfNumbers;
            this.changeCapacity(newCapacity);
        }
        int indexOfAddedNumber;
        for(final Number addedNumber : vector.numbers)
        {
            indexOfAddedNumber = this.amountOfNumbers;
            this.numbers[indexOfAddedNumber] = addedNumber;
            this.amountOfNumbers++;
        }
    }

    public final void addNumbersOfVector(final int indexToStartAdding, final Vector<NumberType> vector)
    {
        if(vector.isEmpty())
        {
            return;
        }
        if(this.numbers.length < this.amountOfNumbers + vector.amountOfNumbers)
        {
            final int newCapacity = this.amountOfNumbers + vector.amountOfNumbers;
            this.changeCapacity(newCapacity);
        }
        for(int i = indexToStartAdding; i < this.amountOfNumbers; i++)
        {
            this.numbers[i + vector.amountOfNumbers] = this.numbers[i];
        }
        for(int i = 0; i < vector.amountOfNumbers; i++)
        {
            this.numbers[indexToStartAdding + i] = vector.numbers[i];
            this.amountOfNumbers++;
        }
    }

    public final boolean isEmpty()
    {
        return this.amountOfNumbers == 0;
    }

    public final boolean isNumberContain(final NumberType researchNumber)
    {
        for(int i = 0; i < this.amountOfNumbers; i++)
        {
            if(researchNumber.equals(this.numbers[i]))
            {
                return true;
            }
        }
        return false;
    }

    public final NumberType findNumber(final int indexOfResearchNumber)
    {
        if(!Vector.VECTOR_VALIDATOR.isValidIndex(indexOfResearchNumber, this))
        {
            throw new VectorSearchingException("Impossible to find number of vector by given not valid"
                    + " index: " + indexOfResearchNumber + ".");
        }
        return (NumberType)this.numbers[indexOfResearchNumber];
    }

    public final void updateNumber(final int indexOfUpdatedNumber, final NumberType newValueOfUpdatedNumber)
    {
        if(!Vector.VECTOR_VALIDATOR.isValidIndex(indexOfUpdatedNumber, this))
        {
            throw new VectorModificationException("Impossible to update number of vector with given not valid"
                    + " index: " + indexOfUpdatedNumber + ".");
        }
        this.numbers[indexOfUpdatedNumber] = newValueOfUpdatedNumber;
    }

    public final void clear()
    {
        this.numbers = new Number[Vector.DEFAULT_INITIAL_CAPACITY];
        this.amountOfNumbers = 0;
    }

    public final int findIndexOfFirstOccurrenceOfNumber(final NumberType researchNumber)
    {
        for(int i = 0; i < this.amountOfNumbers; i++)
        {
            if(this.numbers[i].equals(researchNumber))
            {
                return i;
            }
        }
        throw new VectorSearchingException("Impossible to find number '" + researchNumber + "' in vector: "
                + this + ".");
    }

    public final int findIndexOfLastOccurrenceOfNumber(final NumberType researchNumber)
    {
        for(int i = this.amountOfNumbers - 1; i >= 0; i--)
        {
            if(this.numbers[i].equals(researchNumber))
            {
                return i;
            }
        }
        throw new VectorSearchingException("Impossible to find number '" + researchNumber + "' in vector: "
                + this + ".");
    }

    public final void removeNumber(final int indexOfRemovedNumber)
    {
        if(!Vector.VECTOR_VALIDATOR.isValidIndex(indexOfRemovedNumber, this))
        {
            throw new VectorModificationException("Impossible to remove number from vector by not valid index: "
                    + indexOfRemovedNumber + ".");
        }
        for(int i = indexOfRemovedNumber; i < this.amountOfNumbers; i++)
        {
            this.numbers[i] = this.numbers[i + 1];
        }
        this.amountOfNumbers--;
    }

    @Override
    public final VectorIterator<NumberType> iterator()
    {
        return new VectorIterator<NumberType>(this);
    }

    @Override
    public final boolean equals(final Object otherObject)
    {
        if(this == otherObject)
        {
            return true;
        }
        if(otherObject == null)
        {
            return false;
        }
        if(this.getClass() != otherObject.getClass())
        {
            return false;
        }
        final Vector<NumberType> other = (Vector<NumberType>)otherObject;
        if(this.amountOfNumbers != other.amountOfNumbers)
        {
            return false;
        }
        for(int i = 0; i < this.amountOfNumbers; i++)
        {
            if(!this.numbers[i].equals(other.numbers[i]))
            {
                return false;
            }
        }
        return true;
    }

    public final void trimToAmountOfNumbers()
    {
        this.changeCapacity(this.amountOfNumbers);
    }

    @Override
    public final int hashCode()
    {
        return Arrays.hashCode(this.numbers) + Integer.hashCode(this.amountOfNumbers);
    }

    @Override
    public final String toString()
    {
        return    this.getClass().getSimpleName() + "[numbers = " + this.findDescriptionOfNumbers() + ", "
                + "amountOfNumbers = " + this.amountOfNumbers + "]";
    }

    private final String findDescriptionOfNumbers()
    {
        final StringBuilder descriptionOfNumbers = new StringBuilder();
        final char startOfDescriptionOfNumbers = '[';
        descriptionOfNumbers.append(startOfDescriptionOfNumbers);
        final String delimiterOfNumbers = ", ";
        for(int i = 0; i < this.amountOfNumbers; i++)
        {
            descriptionOfNumbers.append(this.numbers[i]);
            if(i != this.amountOfNumbers - 1)
            {
                descriptionOfNumbers.append(delimiterOfNumbers);
            }
        }
        final char endOfDescriptionOfNumbers = ']';
        descriptionOfNumbers.append(endOfDescriptionOfNumbers);
        return descriptionOfNumbers.toString();
    }
}
