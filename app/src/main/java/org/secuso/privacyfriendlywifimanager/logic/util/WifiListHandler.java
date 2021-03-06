/*
Copyright 2016-2018 Jan Henzel, Patrick Jauernig, Dennis Werner

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package org.secuso.privacyfriendlywifimanager.logic.util;

import android.content.Context;

import org.secuso.privacyfriendlywifimanager.logic.types.WifiLocationEntry;
import org.secuso.privacyfriendlywifimanager.service.ManagerService;

import java.io.IOException;
import java.util.List;
import java.util.Observer;

public class WifiListHandler extends SerializationHelper implements IListHandler<WifiLocationEntry> {
    private static final long serialVersionUID = -7094227865638670543L;
    private static ListHandler<WifiLocationEntry> list;

    /**
     * Creates a new WifiListHandler. All instances work on the same underlying list.
     * @param context A context to use.
     */
    public WifiListHandler(Context context) {
        if (list == null) {
            WifiListHandler.list = new ListHandler<>(context, ManagerService.FN_LOCATION_ENTRIES);
        }
    }

    /**
     * @see SerializationHelper
     */
    public void initialize(Context context, Object[] args) throws IOException {
        if (args[0] instanceof ListHandler) {
            WifiListHandler.list = (ListHandler<WifiLocationEntry>) args[0];
        } else {
            throw new IOException(SerializationHelper.SERIALIZATION_ERROR);
        }
    }

    @Override
    protected Object[] getPersistentFields() {
        return new Object[]{list};
    }

    /**
     * @see IListHandler
     */
    public void addObserver(Observer observer) {
        WifiListHandler.list.addObserver(observer);
    }

    /**
     * @see IListHandler
     */
    public boolean save() {
        return WifiListHandler.list.save();
    }

    /**
     * @see IListHandler
     */
    public List<WifiLocationEntry> getAll() {
        return WifiListHandler.list.getAll();
    }

    /**
     * @see IListHandler
     */
    public WifiLocationEntry get(int location) {
        return WifiListHandler.list.get(location);
    }

    /**
     * @see IListHandler
     */
    public boolean add(WifiLocationEntry newEntry) {
        return WifiListHandler.list.add(newEntry);
    }

    /**
     * @see IListHandler
     */
    public boolean addAll(List<WifiLocationEntry> newEntries) {
        return WifiListHandler.list.addAll(newEntries);
    }

    /**
     * @see IListHandler
     */
    public void sort() {
        WifiListHandler.list.sort();
    }

    /**
     * @see IListHandler
     */
    public boolean remove(WifiLocationEntry entry) {
        return WifiListHandler.list.remove(entry);
    }

    /**
     * @see IListHandler
     */
    public int size() {
        return WifiListHandler.list.size();
    }

    /**
     * @see IListHandler
     */
    public int indexOf(Object o) {
        return WifiListHandler.list.indexOf(o);
    }

    /**
     * @see IListHandler
     */
    public boolean isEmpty() {
        return WifiListHandler.list.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<WifiLocationEntry> entries = this.getAll();

        sb.append("### Wi-Fi List ###\n");
        for (int i = 0; i < entries.size(); i++) {
            sb.append("[").append(entries.get(i).toString()).append("]\n");
        }

        return sb.toString();
    }
}
