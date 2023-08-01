import React from 'react';
import ReactDOM from 'react-dom';
import { Form, Input, Message } from '@alifd/next';
import axios from 'axios';
import './styles/login.scss';
import { serverUrl } from './utils/constants';
import { checkLoginStatus } from './utils';

const formItemLayout = {
  labelCol: { fixedSpan: 3 },
};

class PageContainer extends React.Component {
  state = {};

  handleSubmit = async (values: any, errors: any) => {
    if (errors) return;
    try {
      const res = await axios.request({
        method: 'post',
        baseURL: serverUrl,
        url: '/api/user/login',
        data: values,
        withCredentials: true,
      });
      if (res.data?.data) {
        window.location.href = 'editor.html';
      } else {
        Message.error(res.data?.message || '登录失败');
      }
    } catch (e: any) {
      console.error(e);
      Message.error(e.message);
    }
  };

  render() {
    checkLoginStatus().then((isLogin) => {
      if (isLogin) {
        window.location.href = 'editor.html';
      }
    });

    return (
      <>
        <img src="img/logo.png" width={70}></img>
        <h1>Low Code Engine</h1>
        <Form
          style={{ width: 350 }}
          {...formItemLayout}
          labelTextAlign="left"
          size="large"
          labelAlign="inset"
        >
          <Form.Item
            name="username"
            label="用户名"
            required
            requiredMessage="请输入用户名"
            asterisk={false}
          >
            <Input trim />
          </Form.Item>
          <Form.Item
            name="password"
            label="密码"
            required
            requiredMessage="请输入密码"
            asterisk={false}
          >
            <Input.Password trim />
          </Form.Item>
          <Form.Item label=" ">
            <Form.Submit
              style={{ width: '100%' }}
              type="primary"
              validate
              onClick={this.handleSubmit}
            >
              登录
            </Form.Submit>
          </Form.Item>
        </Form>
      </>
    );
  }
}

ReactDOM.render(<PageContainer />, document.getElementById('ice-container'));
