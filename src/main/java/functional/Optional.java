package functional;

import vo.Account;

public class Optional<T> {
    public static final Optional NULL = new Optional(null);
    private final T value;

    public Optional(T value) {
        this.value = value;
    }

    public static Optional<Account> from(Account account) {
        return new Optional<Account>(account);
    }

    public <R> Optional<R> map(Function<T, R> transform) {
        if (this.value != null) {
            return new Optional<R>(transform.apply(this.value));
        }
        return NULL;
    }

    public T getValue() {
        return value;
    }
}
