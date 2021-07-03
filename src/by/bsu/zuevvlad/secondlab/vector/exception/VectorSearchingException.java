package by.bsu.zuevvlad.secondlab.vector.exception;

public final class VectorSearchingException extends VectorException
{
    public VectorSearchingException()
    {
        super();
    }

    public VectorSearchingException(final String description)
    {
        super(description);
    }

    public VectorSearchingException(final Exception cause)
    {
        super(cause);
    }

    public VectorSearchingException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
