/*
 * Copyright 2008-2009 the original ������(zyc@hasor.net).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hasor.mvc.resource.loader.creator;
import java.io.File;
import java.io.IOException;
import org.hasor.Hasor;
import org.hasor.context.AppContext;
import org.hasor.context.XmlProperty;
import org.hasor.mvc.resource.ResourceLoader;
import org.hasor.mvc.resource.ResourceLoaderCreator;
import org.hasor.mvc.resource.ResourceLoaderDefine;
import org.hasor.mvc.resource.loader.ZipResourceLoader;
import org.more.util.StringUtils;
/**
 * ���ڴ���һ�����Դ�zip�л�ȡ��Դ��ResourceLoader��
 * @version : 2013-6-6
 * @author ������ (zyc@hasor.net)
 */
@ResourceLoaderDefine(configElement = "ZipLoader")
public class ZipResourceLoaderCreator implements ResourceLoaderCreator {
    @Override
    public ResourceLoader newInstance(AppContext appContext, XmlProperty xmlConfig) throws IOException {
        String body = xmlConfig.getText();
        body = StringUtils.isBlank(body) ? "" : body;
        body = appContext.getEnvironment().evalString(body);
        File fileBody = new File(body);
        if (fileBody.exists() == false || fileBody.isDirectory())
            return null;
        Hasor.info("loadZip %s -> %s", xmlConfig.getText(), fileBody);
        ZipResourceLoader dirTemplateLoader = new ZipResourceLoader(fileBody.getAbsolutePath());
        return dirTemplateLoader;
    }
}