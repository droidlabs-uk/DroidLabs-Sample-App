package com.droidlabs.core.network

import kotlinx.coroutines.flow.Flow

class ResourceGroup<in Input, in Key, out Output>(
    remoteGroupFetch: suspend (Input) -> List<Output>?,
    localGroupFetch: suspend (Input) -> List<Output>?,
    localGroupStore: suspend (List<Output>) -> Unit,
    private val remoteFetch: suspend (Key, Input) -> Output?,
    private val localFetch: suspend (Key, Input) -> Output?,
    private val localStore: suspend (Output) -> Unit,
    localDelete: suspend () -> Unit,
    private val refreshControl: RefreshControl = RefreshControl()
) : TimeLimitedResource by refreshControl {

    private val groupResource = Resource(
        remoteGroupFetch,
        localGroupFetch,
        localGroupStore,
        localDelete,
        refreshControl
    )

    private val resourceMap: MutableMap<Key, Resource<Input, Output>> = mutableMapOf()

    suspend fun query(args: Input, force: Boolean = false): Flow<List<Output>?> =
        groupResource.query(args, force)

    suspend fun queryByKey(key: Key, args: Input, force: Boolean = false): Flow<Output?> =
        singleResource(key).query(args, force)

    private fun singleResource(key: Key) =
        resourceMap[key] ?: Resource<Input, Output>(
            remoteFetch = { remoteFetch(key, it) },
            localFetch = { localFetch(key, it) },
            localStore = { localStore(it) },
            localDelete = { },
            refreshControl = refreshControl.createChild()
        ).also { resourceMap[key] = it }
}
