package by.bsu.zuevvlad.secondlab.vector.iterator.exception;

public class VectorIteratorException extends RuntimeException
{
    public VectorIteratorException()
    {
        super();
    }

    public VectorIteratorException(final String description)
    {
        super(description);
    }

    public VectorIteratorException(final Exception cause)
    {
        super(cause);
    }

    public VectorIteratorException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
