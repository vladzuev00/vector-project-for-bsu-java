package by.bsu.zuevvlad.secondlab.vector.exception;

public class VectorException extends RuntimeException
{
    public VectorException()
    {
        super();
    }

    public VectorException(final String description)
    {
        super(description);
    }

    public VectorException(final Exception cause)
    {
        super(cause);
    }

    public VectorException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
