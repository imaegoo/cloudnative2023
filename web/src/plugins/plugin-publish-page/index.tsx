import { IPublicModelPluginContext, IPublicEnumTransformStage } from '@alilc/lowcode-types';
import { material, project } from '@alilc/lowcode-engine';
import { filterPackages } from '@alilc/lowcode-plugin-inject';
import { Button, Dialog, Form, Input, Message } from '@alifd/next';
import * as React from 'react';
import axios from 'axios';

const FormItem = Form.Item;

class SkeletonContent extends React.Component {
  constructor(...args) {
    super(...args);
    this.state = {
      title: '',
      visible: false,
      loading: false
    };
    this.onOpen = () => {
      this.setState({ visible: true });
    };
    this.onClose = () => {
      this.setState({ visible: false });
    };
    this.doPublish = async () => {
      this.setState({ loading: true });
      try {
        const data = {
          title: this.state.title,
          content: JSON.stringify({
            schema: project.exportSchema(IPublicEnumTransformStage.Save),
            packages: await filterPackages(material.getAssets()?.packages)
          })
        };
        // TODO: 等后端部署好以后这里改成后端地址
        const res = await axios.request({
          method: 'post',
          url: '/api/publish',
          data
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
  }
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
          okProps={{ loading: this.state.loading }}
          onCancel={this.onClose}
          onClose={this.onClose}
        >
          <Form style={{width: "300px"}}>
            <FormItem
              name="title"
              label="标题"
            >
              <Input value={this.state.title} onChange={e => this.setState({ title: e })} />
            </FormItem>
          </Form>
        </Dialog>
      </>
    );
  }
}

// 发布页面
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
}

PublishPagePlugin.pluginName = 'PublishPagePlugin';
PublishPagePlugin.meta = {
  dependencies: ['EditorInitPlugin'],
};
export default PublishPagePlugin;
