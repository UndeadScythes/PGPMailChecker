/*
 * Copyright (C) 2014 UndeadScythes <udscythes@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.undeadscythes.pgpmailchecker.mail;

import com.undeadscythes.ceremail.Mail;
import com.undeadscythes.ceremail.MailSort;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.AbstractListModel;

/**
 *
 * @author UndeadScythes <udscythes@gmail.com>
 */
public class MailListModel extends AbstractListModel<Mail> {
    private static final long serialVersionUID = 1L;
    private final ArrayList<Mail> mails = new ArrayList<>(0);
    
    public void addMail(Mail mail) {
        mails.add(mail);
        mails.sort(new MailSort());
        int index = mails.indexOf(mail);
        fireIntervalAdded(this, index, index);
    }
    
    public void clear() {
        mails.clear();
    }
    
    public void addMail(Collection<? extends Mail> newMail) {
        for (Mail mail : newMail) {
            addMail(mail);
        }
    }
    
    @Override
    public int getSize() {
        return mails.size();
    }

    @Override
    public Mail getElementAt(int index) {
        return index < mails.size() ? mails.get(index) : null;
    }
}
