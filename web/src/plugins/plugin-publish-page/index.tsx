import { IPublicModelPluginContext, IPublicEnumTransformStage } from '@alilc/lowcode-types';
import { material, project } from '@alilc/lowcode-engine';
import { filterPackages } from '@alilc/lowcode-plugin-inject';
import { Button, Dialog, Form, Input, Message } from '@alifd/next';
import * as React from 'react';
import axios from 'axios';
import { serverUrl } from 'src/utils/constants';

class SkeletonContent extends React.Component {
  state = {
    pageId: '',
    title: '',
    visible: false,
    loading: false,
  };
  onOpen = () => {
    this.setState({ visible: true });
  };
  onClose = () => {
    this.setState({
      pageId: '',
      title: '',
      visible: false,
      loading: false,
    });
  };
  doPublish = async () => {
    this.setState({ loading: true });
    try {
      const data = {
        pageId: this.state.pageId,
        title: this.state.title,
        content: JSON.stringify({
          schema: project.exportSchema(IPublicEnumTransformStage.Save),
          packages: await filterPackages(material.getAssets()?.packages),
        }),
      };
      const res = await axios.request({
        method: 'post',
        baseURL: serverUrl,
        url: '/api/page/publish',
        data,
        withCredentials: true,
      });
      if (res) {
        this.onClose();
      }
    } catch (e: any) {
      console.error(e);
      Message.error(e.message);
    } finally {
      this.setState({ loading: false });
    }
  };
  render() {
    return (
      <>
        <Button type="primary" onClick={() => this.onOpen()}>
          发布
        </Button>
        <Dialog
          v2
          title="发布"
          visible={this.state.visible}
          onOk={this.doPublish}
          okProps={{
            loading: this.state.loading,
            disabled: !this.state.pageId || !this.state.title,
          }}
          onCancel={this.onClose}
          onClose={this.onClose}
        >
          <p>
            <ol>
              <li>1. 请填写三级域名以发布网页到互联网。</li>
              <li>2. 同一域名重复发布将会覆盖之前发布的内容。</li>
              <li>3. 无法使用已被他人使用的域名。</li>
            </ol>
          </p>
          <Form style={{ width: '500px' }}>
            <Form.Item name="pageId" label="三级域名" required>
              <Input
                value={this.state.pageId}
                onChange={(e) => this.setState({ pageId: e })}
                addonTextBefore="http://"
                addonTextAfter=".lowcode.imaegoo.com:8080"
              />
            </Form.Item>
            <Form.Item name="title" label="网页标题" required>
              <Input value={this.state.title} onChange={(e) => this.setState({ title: e })} />
            </Form.Item>
          </Form>
        </Dialog>
      </>
    );
  }
}

const PublishPagePlugin = (ctx: IPublicModelPluginContext) => {
  return {
    async init() {
      const { skeleton } = ctx;
      skeleton.add({
        name: 'publishPage',
        area: 'topArea',
        type: 'Widget',
        props: {
          align: 'right',
        },
        content: SkeletonContent,
      });
    },
  };
};

PublishPagePlugin.pluginName = 'PublishPagePlugin';
PublishPagePlugin.meta = {
  dependencies: ['EditorInitPlugin'],
};
export default PublishPagePlugin;
