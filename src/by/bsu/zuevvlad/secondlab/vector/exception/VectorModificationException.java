package by.bsu.zuevvlad.secondlab.vector.exception;

public final class VectorModificationException extends VectorException
{
    public VectorModificationException()
    {
        super();
    }

    public VectorModificationException(final String description)
    {
        super(description);
    }

    public VectorModificationException(final Exception cause)
    {
        super(cause);
    }

    public VectorModificationException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
