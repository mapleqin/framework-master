/*******************************************************************************
 * Copyright 2013-2014 Toaker framework-master
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use mDbUtils file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.toaker.commons.db;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;

import com.toaker.commons.db.exception.DbException;
import com.toaker.commons.db.sqlite.DbModelSelector;
import com.toaker.commons.db.sqlite.Selector;
import com.toaker.commons.db.sqlite.SqlInfo;
import com.toaker.commons.db.sqlite.WhereBuilder;
import com.toaker.commons.db.table.DbModel;
import com.toaker.commons.db.utils.IOUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Decorator for framework-master
 *
 * @author Toaker [Toaker](ToakerQin@gmail.com)
 *         [Toaker](http://www.toaker.com)
 * @Description:
 * @Time Create by 2015/4/19 18:58
 */
public class NativeDbManager {

    protected static Map<String,NativeDbManager> mDbManagers;

    protected Context mContext;

    protected DbUtils        mDbUtils;

    protected String         mDbName;

    protected NativeDbManager(Context context,String fileName){
        this.mContext = context;
        this.mDbName = fileName;
        String path = IOUtils.copyDatabase(mContext, fileName);
        if(TextUtils.isEmpty(path)){
            throw new IllegalStateException("The NativeDbManager file copy failed!");
        }
        DbUtils.DaoConfig daoConfig = new DbUtils.DaoConfig(mContext);
        daoConfig.setDbDir(mContext.getFilesDir().getAbsolutePath());
        daoConfig.setDbName(fileName);
        daoConfig.setDbVersion(1);
        mDbUtils = DbUtils.create(daoConfig);
    }

    public static void init(Context context,String dbName){
        if(mDbManagers == null){
            mDbManagers = new HashMap<String, NativeDbManager>();
        }
        if(!mDbManagers.containsKey(dbName)){
            mDbManagers.put(dbName,new NativeDbManager(context,dbName));
        }
    }

    public static NativeDbManager getInstance(String dbName){
        if(mDbManagers == null){
           throw new IllegalStateException("The NativeDbManager not initialized");
        }
        return mDbManagers.get(dbName);
    }


    public void saveOrUpdate(Object entity) throws DbException {
        mDbUtils.saveOrUpdate(entity);
    }

    public void saveOrUpdateAll(List<?> entities) throws DbException {
        mDbUtils.saveOrUpdateAll(entities);
    }

    public void replace(Object entity) throws DbException {
        mDbUtils.replace(entity);
    }

    public void replaceAll(List<?> entities) throws DbException {
        mDbUtils.replaceAll(entities);
    }

    public void save(Object entity) throws DbException {
        mDbUtils.save(entity);
    }

    public void saveAll(List<?> entities) throws DbException {
        mDbUtils.saveAll(entities);
    }

    public boolean saveBindingId(Object entity) throws DbException {
        return mDbUtils.saveBindingId(entity);
    }

    public void saveBindingIdAll(List<?> entities) throws DbException {
        mDbUtils.saveBindingIdAll(entities);
    }

    public void deleteById(Class<?> entityType, Object idValue) throws DbException {
        mDbUtils.deleteById(entityType,idValue);
    }

    public void delete(Object entity) throws DbException {
        mDbUtils.delete(entity);
    }

    public void delete(Class<?> entityType, WhereBuilder whereBuilder) throws DbException {
        mDbUtils.delete(entityType,whereBuilder);
    }

    public void deleteAll(List<?> entities) throws DbException {
        mDbUtils.deleteAll(entities);
    }

    public void deleteAll(Class<?> entityType) throws DbException {
        delete(entityType, null);
    }

    public void update(Object entity, String... updateColumnNames) throws DbException {
        mDbUtils.update(entity,updateColumnNames);
    }

    public void update(Object entity, WhereBuilder whereBuilder, String... updateColumnNames) throws DbException {
        mDbUtils.update(entity,whereBuilder,updateColumnNames);
    }

    public void updateAll(List<?> entities, String... updateColumnNames) throws DbException {
        mDbUtils.updateAll(entities,updateColumnNames);
    }

    public void updateAll(List<?> entities, WhereBuilder whereBuilder, String... updateColumnNames) throws DbException {
        mDbUtils.updateAll(entities,whereBuilder,updateColumnNames);
    }

    @SuppressWarnings("unchecked")
    public <T> T findById(Class<T> entityType, Object idValue) throws DbException {
        return mDbUtils.findById(entityType,idValue);
    }

    @SuppressWarnings("unchecked")
    public <T> T findFirst(Selector selector) throws DbException {
        return mDbUtils.findFirst(selector);
    }

    public <T> T findFirst(Class<T> entityType) throws DbException {
        return findFirst(Selector.from(entityType));
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> findAll(Selector selector) throws DbException {
        return mDbUtils.findAll(selector);
    }

    public <T> List<T> findAll(Class<T> entityType) throws DbException {
        return findAll(Selector.from(entityType));
    }

    public DbModel findDbModelFirst(SqlInfo sqlInfo) throws DbException {
        return mDbUtils.findDbModelFirst(sqlInfo);
    }

    public DbModel findDbModelFirst(DbModelSelector selector) throws DbException {
        return mDbUtils.findDbModelFirst(selector);
    }

    public List<DbModel> findDbModelAll(SqlInfo sqlInfo) throws DbException {
        return mDbUtils.findDbModelAll(sqlInfo);
    }

    public List<DbModel> findDbModelAll(DbModelSelector selector) throws DbException {
        return mDbUtils.findDbModelAll(selector);
    }

    public long count(Selector selector) throws DbException {
        return mDbUtils.count(selector);
    }

    public long count(Class<?> entityType) throws DbException {
        return count(Selector.from(entityType));
    }

    public void createTableIfNotExist(Class<?> entityType) throws DbException {
        mDbUtils.createTableIfNotExist(entityType);
    }

    public boolean tableIsExist(Class<?> entityType) throws DbException {
        return mDbUtils.tableIsExist(entityType);
    }

    public void dropDb() throws DbException {
       mDbUtils.dropDb();
    }

    public void dropTable(Class<?> entityType) throws DbException {
        mDbUtils.dropTable(entityType);
    }

    public void close() {
        mDbUtils.close();
    }

    public void execNonQuery(SqlInfo sqlInfo) throws DbException {
        mDbUtils.execNonQuery(sqlInfo);
    }

    public void execNonQuery(String sql) throws DbException {
        mDbUtils.execNonQuery(sql);
    }

    public Cursor execQuery(SqlInfo sqlInfo) throws DbException {
        return mDbUtils.execQuery(sqlInfo);
    }

    public Cursor execQuery(String sql) throws DbException {
        return mDbUtils.execQuery(sql);
    }
}
