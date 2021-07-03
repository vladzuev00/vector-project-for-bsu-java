package by.bsu.zuevvlad.secondlab.vector.exception;

public final class VectorCreatingException extends VectorException
{
    public VectorCreatingException()
    {
        super();
    }

    public VectorCreatingException(final String description)
    {
        super(description);
    }

    public VectorCreatingException(final Exception cause)
    {
        super(cause);
    }

    public VectorCreatingException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
