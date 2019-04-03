package Observer;

import model.subject.Subject;

/**
 * cette interface represente les methodes a implementer pour observer un sujet
 */
public interface Observer {

    /**
     * @param subject sujet ayant subi des modifications devant être notifier a ses observateurs
     */
    void update(Subject subject);
}