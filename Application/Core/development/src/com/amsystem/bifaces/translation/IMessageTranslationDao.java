package com.amsystem.bifaces.translation;

import java.util.List;

/**
 * Title: IMessageTranslationDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 10/03/2017.
 */

public interface IMessageTranslationDao {

    MessageTranslationDao findById(MessageTranslationPK id);

    void save(MessageTranslationDao messageTranslationDao);

    List<MessageTranslation> findAllMessage();
}
