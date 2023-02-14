package mk.finki.ukim.mk.wp.lab.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserNameConverter implements AttributeConverter<UserFullName,String> {


    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(UserFullName user) {
        if(user == null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (user.getSurname() != null && !user.getSurname()
                .isEmpty()) {
            sb.append(user.getSurname());
            sb.append(SEPARATOR);
        }

        if (user.getName() != null
                && !user.getName().isEmpty()) {
            sb.append(user.getName());
        }

        return sb.toString();
    }

    @Override
    public UserFullName convertToEntityAttribute(String userName) {
        if (userName == null || userName.isEmpty()) {
            return null;
        }

        String[] pieces = userName.split(SEPARATOR);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

        UserFullName user = new UserFullName();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (userName.contains(SEPARATOR)) {
            user.setSurname(firstPiece);

            if (pieces.length >= 2 && pieces[1] != null
                    && !pieces[1].isEmpty()) {
                user.setName(pieces[1]);
            }
        } else {
            user.setName(firstPiece);
        }

        return user;
    }
}
