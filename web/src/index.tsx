import ReactDOM from 'react-dom';
import React, { useState } from 'react';
import { Loading } from '@alifd/next';
import mergeWith from 'lodash/mergeWith';
import isArray from 'lodash/isArray';
import { buildComponents, assetBundle, AssetLevel, AssetLoader } from '@alilc/lowcode-utils';
import ReactRenderer from '@alilc/lowcode-react-renderer';
import { injectComponents } from '@alilc/lowcode-plugin-inject';
import axios from 'axios';
import appHelper from './appHelper';
import { getPreviewLocale, setPreviewLocale } from './services/mockService';
import { serverUrl } from './utils/constants';

function getPageId() {
  return window.location.hostname.split('.')[0];
}

const PageContainer = () => {
  const [data, setData] = useState({});

  async function init() {
    const pageId = getPageId();
    const res = await axios.request({
      baseURL: serverUrl,
      url: '/api/page/get',
      params: { pageId },
    });
    const { title, content } = res.data;
    const { schema, packages } = JSON.parse(content);
    document.head.title = title;

    const {
      componentsMap: componentsMapArray,
      componentsTree,
      i18n,
      dataSource: projectDataSource,
    } = schema;
    const componentsMap: any = {};
    componentsMapArray.forEach((component: any) => {
      componentsMap[component.componentName] = component;
    });
    const pageSchema = componentsTree[0];

    const libraryMap = {};
    const libraryAsset = [];
    packages.forEach(({ package: _package, library, urls, renderUrls }) => {
      libraryMap[_package] = library;
      if (renderUrls) {
        libraryAsset.push(renderUrls);
      } else if (urls) {
        libraryAsset.push(urls);
      }
    });

    const vendors = [assetBundle(libraryAsset, AssetLevel.Library)];

    // TODO asset may cause pollution
    const assetLoader = new AssetLoader();
    await assetLoader.load(libraryAsset);
    const components = await injectComponents(buildComponents(libraryMap, componentsMap));

    setData({
      schema: pageSchema,
      components,
      i18n,
      projectDataSource,
    });
  }

  const { schema, components, i18n = {}, projectDataSource = {} } = data as any;

  if (!schema || !components) {
    init();
    return <Loading fullScreen />;
  }
  const currentLocale = getPreviewLocale(getPageId());

  if (!(window as any).setPreviewLocale) {
    // for demo use only, can use this in console to switch language for i18n test
    // 在控制台 window.setPreviewLocale('en-US') 或 window.setPreviewLocale('zh-CN') 查看切换效果
    (window as any).setPreviewLocale = (locale: string) =>
      setPreviewLocale(getPageId(), locale);
  }

  function customizer(objValue: [], srcValue: []) {
    if (isArray(objValue)) {
      return objValue.concat(srcValue || []);
    }
  }

  return (
    <div className="lowcode-page">
      <ReactRenderer
        className="lowcode-page-content"
        schema={{
          ...schema,
          dataSource: mergeWith(schema.dataSource, projectDataSource, customizer),
        }}
        components={components}
        locale={currentLocale}
        messages={i18n}
        appHelper={appHelper}
      />
    </div>
  );
};

/**
 * 根据用户访问的域名来判断是进入网页设计器还是发布的网页
 * 举例：
 * 用户访问 http://lowcode.imaegoo.com:8080/ 就进入网页设计器
 * 用户访问 http://mypage.lowcode.imaegoo.com:8080/ 就进入 pageId 为 mypage 的网页
 */
function isInViewMode() {
  return getPageId() !== 'lowcode';
}

if (isInViewMode()) {
  ReactDOM.render(<PageContainer />, document.getElementById('ice-container'));
} else {
  window.location.href = 'editor.html';
}
