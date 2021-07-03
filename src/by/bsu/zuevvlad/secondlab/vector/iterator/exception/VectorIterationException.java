package by.bsu.zuevvlad.secondlab.vector.iterator.exception;

public final class VectorIterationException extends VectorIteratorException
{
    public VectorIterationException()
    {
        super();
    }

    public VectorIterationException(final String description)
    {
        super(description);
    }

    public VectorIterationException(final Exception cause)
    {
        super(cause);
    }

    public VectorIterationException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
