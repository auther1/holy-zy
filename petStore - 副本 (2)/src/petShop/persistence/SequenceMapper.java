package petShop.persistence;

import petShop.domain.Sequence;

public interface SequenceMapper {
    Sequence getSequence(Sequence sequence);
    void updateSequence(Sequence sequence);
}
