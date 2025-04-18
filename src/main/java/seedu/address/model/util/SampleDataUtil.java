package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyTenantTracker;
import seedu.address.model.TenantTracker;
import seedu.address.model.tag.Tag;
import seedu.address.model.tenant.Address;
import seedu.address.model.tenant.Email;
import seedu.address.model.tenant.Name;
import seedu.address.model.tenant.Phone;
import seedu.address.model.tenant.Tenant;

/**
 * Contains utility methods for populating {@code TenantTracker} with sample data.
 */
public class SampleDataUtil {
    public static Tenant[] getSampleTenants() {
        return new Tenant[] {
            new Tenant(new Name("Alex", "Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40 S123456"), getTagSet("friends"), false,
                    false),
            new Tenant(new Name("Bernice", "Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18 S123456"),
                    getTagSet("colleagues", "friends"), false, false),
            new Tenant(new Name("Charlotte", "Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04 S123457"), getTagSet("neighbours"),
                    false, false),
            new Tenant(new Name("David", "Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43 S123458"), getTagSet("family"),
                    false, false),
            new Tenant(new Name("Irfan", "Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35 S123459"), getTagSet("classmates"),
                    false, false),
            new Tenant(new Name("Roy", "Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31 S123451"), getTagSet("colleagues"),
                    false, false)};
    }

    public static ReadOnlyTenantTracker getSampleTenantTracker() {
        TenantTracker sampleTt = new TenantTracker();
        for (Tenant sampleTenant : getSampleTenants()) {
            sampleTt.addTenant(sampleTenant);
        }
        return sampleTt;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings).map(Tag::new).collect(Collectors.toSet());
    }

}
