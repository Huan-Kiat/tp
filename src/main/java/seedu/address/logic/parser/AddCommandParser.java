package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FAMILY_NAME;
// import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GIVEN_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
// import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
// import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import java.util.stream.Stream;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tenant.Address;
import seedu.address.model.tenant.Email;
import seedu.address.model.tenant.Name;
import seedu.address.model.tenant.Phone;
import seedu.address.model.tenant.Tenant;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {


    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand and returns an
     * AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args, String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GIVEN_NAME, PREFIX_FAMILY_NAME,
                PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG);


        if (!arePrefixesPresent(argMultimap, PREFIX_GIVEN_NAME, PREFIX_FAMILY_NAME, PREFIX_ADDRESS, PREFIX_PHONE,
                PREFIX_EMAIL) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.lastUserInput(userInput) + MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_GIVEN_NAME, PREFIX_FAMILY_NAME, PREFIX_ADDRESS, PREFIX_PHONE,
                PREFIX_EMAIL);

        String givenName = argMultimap.getValue(PREFIX_GIVEN_NAME).get();
        String familyName = argMultimap.getValue(PREFIX_FAMILY_NAME).get();

        Name name = ParserUtil.parseName(userInput, givenName, familyName);
        Address address = ParserUtil.parseAddress(userInput, argMultimap.getValue(PREFIX_ADDRESS).get());
        Phone phone = ParserUtil.parsePhone(userInput, argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(userInput, argMultimap.getValue(PREFIX_EMAIL).get());
        Set<Tag> tagList = ParserUtil.parseTags(userInput, argMultimap.getAllValues(PREFIX_TAG));

        Tenant person = new Tenant(name, phone, email, address, tagList);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());

    }

}
