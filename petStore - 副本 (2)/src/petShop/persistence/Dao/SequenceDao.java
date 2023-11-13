package petShop.persistence.Dao;

import petShop.domain.Sequence;

public interface SequenceDao {

    Sequence getSequence(Sequence sequence);

    void updateSequence(Sequence sequence);

}
