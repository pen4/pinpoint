/*
 * Copyright 2020 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.bootstrap.plugin.uri;

import com.navercorp.pinpoint.common.trace.UriExtractorType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Taejin Koo
 */
public class UriExtractorProviderRegistryTest {

    @Test
    public void providerRegistryTest() {
        String[] parameterArray1 = {"hello"};
        UriMappingExtractorProvider provider1
                = new UriMappingExtractorProvider(UriExtractorType.SERVLET_REQUEST_ATTRIBUTE, parameterArray1);

        String[] parameterArray2 = {"hi"};
        UriMappingExtractorProvider provider2
                = new UriMappingExtractorProvider(UriExtractorType.SERVLET_REQUEST_ATTRIBUTE, parameterArray2);

        List<UriExtractorProvider> uriExtractorProviderList = new ArrayList<>();
        uriExtractorProviderList.add(provider1);
        uriExtractorProviderList.add(provider2);


        UriExtractorProviderRegistry registry = new UriExtractorProviderRegistry(uriExtractorProviderList);
        List<UriMappingExtractorProvider> result = registry.getUriExtractorProvider(UriMappingExtractorProvider.class, UriExtractorType.SERVLET_REQUEST_ATTRIBUTE);
        Assertions.assertEquals(2, result.size());

        result = registry.getUriExtractorProvider(UriMappingExtractorProvider.class, UriExtractorType.NONE);
        Assertions.assertEquals(0, result.size());

        List<MockUriExtractorProvider> result2 = registry.getUriExtractorProvider(MockUriExtractorProvider.class, UriExtractorType.SERVLET_REQUEST_ATTRIBUTE);
        Assertions.assertEquals(0, result2.size());
    }

    static class MockUriExtractorProvider implements UriExtractorProvider {

        @Override
        public UriExtractorType getUriExtractorType() {
            return null;
        }

    }

}
