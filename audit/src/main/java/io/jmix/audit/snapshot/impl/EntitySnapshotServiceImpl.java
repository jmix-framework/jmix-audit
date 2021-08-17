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

package io.jmix.audit.snapshot.impl;

import io.jmix.audit.snapshot.EntitySnapshotAPI;
import io.jmix.audit.snapshot.EntitySnapshotService;
import io.jmix.audit.snapshot.datastore.model.EntitySnapshotModel;
import io.jmix.core.FetchPlan;
import io.jmix.core.metamodel.model.MetaClass;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component("audit_EntitySnapshotService")
public class EntitySnapshotServiceImpl implements EntitySnapshotService {

    private final EntitySnapshotAPI entitySnapshotAPI;

    public EntitySnapshotServiceImpl(EntitySnapshotAPI entitySnapshotAPI) {
        this.entitySnapshotAPI = entitySnapshotAPI;
    }

    @Override
    public List<EntitySnapshotModel> getSnapshots(MetaClass metaClass, Object id) {
        return entitySnapshotAPI.getSnapshots(metaClass, id);
    }

    @Override
    public List<EntitySnapshotModel> getSnapshots(Object entity) {
        if (entity == null) {
            return Collections.emptyList();
        }
        return entitySnapshotAPI.getSnapshots(entity);
    }

    @Override
    public EntitySnapshotModel createSnapshot(Object entity, FetchPlan fetchPlan) {
        return entitySnapshotAPI.createSnapshot(entity, fetchPlan);
    }

    @Override
    public EntitySnapshotModel createSnapshot(Object entity, FetchPlan fetchPlan, Date snapshotDate) {
        return entitySnapshotAPI.createSnapshot(entity,fetchPlan, snapshotDate);
    }

    @Override
    public EntitySnapshotModel createSnapshot(Object entity, FetchPlan fetchPlan, Date snapshotDate, String authorUsername) {
        return entitySnapshotAPI.createSnapshot(entity, fetchPlan, snapshotDate, authorUsername);
    }

    @Override
    public Object extractEntity(EntitySnapshotModel snapshot) {
        return entitySnapshotAPI.extractEntity(snapshot);
    }

    @Override
    public void migrateSnapshots(MetaClass metaClass, Object id, Map<Class, Class> classMapping) {
        entitySnapshotAPI.migrateSnapshots(metaClass, id, classMapping);
    }

    @Nullable
    @Override
    public EntitySnapshotModel getLastEntitySnapshot(Object entity) {
        return entitySnapshotAPI.getLastEntitySnapshot(entity);
    }

    @Nullable
    @Override
    public EntitySnapshotModel getLastEntitySnapshot(MetaClass metaClass, Object referenceId) {
        return entitySnapshotAPI.getLastEntitySnapshot(metaClass, referenceId);
    }

    @Override
    public EntitySnapshotModel createTempSnapshot(Object entity, FetchPlan fetchPlan) {
        return entitySnapshotAPI.createTempSnapshot(entity, fetchPlan);
    }

    @Override
    public EntitySnapshotModel createTempSnapshot(Object entity, FetchPlan fetchPlan, Date snapshotDate) {
        return entitySnapshotAPI.createTempSnapshot(entity, fetchPlan, snapshotDate);
    }

    @Override
    public EntitySnapshotModel createTempSnapshot(Object entity, FetchPlan fetchPlan, Date snapshotDate, String authorUsername) {
        return entitySnapshotAPI.createTempSnapshot(entity, fetchPlan, snapshotDate, authorUsername);
    }
}
