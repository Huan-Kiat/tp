package seedu.address.model.tenant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tenant's address in the address book. Guarantees: immutable; is valid as declared in
 * {@link #isValidAddress(String)}
 */
public class Address {

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it should not be blank. "
            + "It must contain a valid 6-digit Singaporean postcode formatted as: SABXXXX, where AB is a 2-digit number"
            + "from 01-82 excluding 74, and XXXX is any 4-digit number. For example, 'S123456' is a valid postcode.";

    /*
     * The first character of the address must not be a whitespace, otherwise " " (a blank string)
     * becomes a valid input. It must contain a 6-digit Singaporean postcode.
     */
    public static final String VALIDATION_REGEX =
            "(?:^|.*\\s)S" + "(?:(0[1-9]|[1-6][0-9]|7[0-3]|7[5-9]|8[0-2]))\\d{4}" + "(?:\\s.*|$)";


    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public Address(String address) {
        requireNonNull(address);
        checkArgument(isValidAddress(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Address)) {
            return false;
        }

        Address otherAddress = (Address) other;
        return value.equals(otherAddress.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
