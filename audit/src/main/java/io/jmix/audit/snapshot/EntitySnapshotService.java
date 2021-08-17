/*
 * Copyright 2021 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.jmix.audit.snapshot;

import io.jmix.audit.snapshot.datastore.model.EntitySnapshotModel;
import io.jmix.core.FetchPlan;
import io.jmix.core.metamodel.model.MetaClass;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Provides entity snapshots functionality.
 */
public interface EntitySnapshotService {

    /**
     * Get snapshots for entity by id
     * @param metaClass Entity meta class
     * @param id Entity id
     * @return Snapshot list
     */
    List<EntitySnapshotModel> getSnapshots(MetaClass metaClass, Object id);

    /**
     * Get snapshots for entity
     * @param entity Entity object
     * @return Snapshot list
     */
    List<EntitySnapshotModel> getSnapshots(Object entity);

    /**
     * Create snapshot for entity and save it to database
     * @param entity Entity
     * @param fetchPlan FetchPlan
     * @return Snapshot
     */
    EntitySnapshotModel createSnapshot(Object entity, FetchPlan fetchPlan);

    /**
     * Create snapshot for Entity with specific date and store it to database
     *
     * @param entity       Entity
     * @param fetchPlan         FetchPlan
     * @param snapshotDate Date
     * @return Snapshot
     */
    EntitySnapshotModel createSnapshot(Object entity, FetchPlan fetchPlan, Date snapshotDate);

    /**
     * Create snapshot for Entity with specific date and author and store it to database
     *
     * @param entity            Entity
     * @param fetchPlan         FetchPlan
     * @param snapshotDate      Date
     * @param authorUsername    Author
     * @return Snapshot
     */
    EntitySnapshotModel createSnapshot(Object entity, FetchPlan fetchPlan, Date snapshotDate, String authorUsername);

    /**
     * Get entity from snapshot
     * @param snapshot Snapshot
     * @return Entity
     */
    Object extractEntity(EntitySnapshotModel snapshot);

    /**
     * Translate snapshots for archival classes
     *
     * @param metaClass    Metaclass
     * @param id           Entity Id
     * @param classMapping Map of [OldClass -&gt; NewClass] for migration
     */
    void migrateSnapshots(MetaClass metaClass, Object id, Map<Class, Class> classMapping);

    /**
     * Get the last snapshot for the given entity. This method always starts a new transaction.
     * It can be used for entities with composite key if they have UUID.
     *
     * @param entity entity
     * @return snapshot or null if there is no snapshots in database for the given entity
     */
    @Nullable
    EntitySnapshotModel getLastEntitySnapshot(Object entity);

    /**
     * Get the last snapshot for the given entity by id. This method always starts a new transaction.
     *
     * @param metaClass   entity meta class
     * @param referenceId reference id for which snapshot refers
     * @return snapshot or null if there is no snapshots in database for the given entity
     */
    @Nullable
    EntitySnapshotModel getLastEntitySnapshot(MetaClass metaClass, Object referenceId);

    /**
     * Creates non-persistent snapshot for entity. It can be used for entities with composite key if they have UUID.
     *
     * @param entity entity
     * @param fetchPlan   fetchPlan
     * @return not persistence snapshot
     */
    EntitySnapshotModel createTempSnapshot(Object entity, FetchPlan fetchPlan);

    /**
     * Creates non-persistent snapshot for entity with a specific date. It can be used for entities with composite
     * key if they have UUID.
     *
     * @param entity       entity
     * @param fetchPlan    entity fetchPlan
     * @param snapshotDate date
     * @return not persistence snapshot
     */
    EntitySnapshotModel createTempSnapshot(Object entity, FetchPlan fetchPlan, Date snapshotDate);

    /**
     * Creates non-persistent snapshot for entity with a specific date and author. It can be used for entities with
     * composite key if they have UUID.
     *
     * @param entity            entity
     * @param fetchPlan         entity fetchPlan
     * @param snapshotDate      date
     * @param authorUsername    author
     * @return not persistence snapshot
     */
    EntitySnapshotModel createTempSnapshot(Object entity, FetchPlan fetchPlan, Date snapshotDate, String authorUsername);
}
