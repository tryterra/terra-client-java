/*
 * Copyright 2022 Terra Enabling Developers Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.tryterra.terraclient.impl;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.*;

public class OkHttp3AsyncCall implements Callback, Future<Response> {
    private final Call call;
    private final CompletableFuture<Response> future;

    public OkHttp3AsyncCall(Call call) {
        this.call = call;
        call.enqueue(this);
        future = new CompletableFuture<>();
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) {
        future.complete(response);
    }

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        future.completeExceptionally(e);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        var out = future.cancel(mayInterruptIfRunning);
        call.cancel();
        return out;
    }

    @Override
    public Response get() throws ExecutionException, InterruptedException {
        return future.get();
    }

    @Override
    public Response get(long timeout, @NotNull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return future.get(timeout, unit);
    }

    @Override
    public boolean isDone() {
        return future.isDone();
    }

    @Override
    public boolean isCancelled() {
        return future.isCancelled();
    }

    public CompletionStage<Response> asCompletionStage() {
        return future;
    }
}