/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObserverObserverable;

import java.beans.PropertyChangeEvent;

/**
 *
 * @author Martijn
 */
public interface PropertyListener {
    /**
     * aangeroepen op inform
     * @param ev
     */
    void PropertyChange(PropertyChangeEvent ev);
}
