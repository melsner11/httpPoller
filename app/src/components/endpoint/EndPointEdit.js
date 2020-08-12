import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from '../AppNavbar';

class EndPointEdit extends Component {

  emptyItem = {
    url: '',

  };

  constructor(props) {
    super(props);
    this.state = {
      item: this.emptyItem
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    if (this.props.match.params.id !== 'new') {
      const endpoint = await (await fetch(`/api/endpoint/${this.props.match.params.id}`)).json();
      this.setState({item: endpoint});
    }
  }

  handleChange(event) {
    let item = {...this.state.item};
    item[event.target.name] = event.target.value;
    this.setState({item});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;
    await fetch('/api/endpoint', {
      method: (item.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(item),
    });
    this.props.history.push('/endpoints');
  }

  render() {
    const {item} = this.state;
    const title = <h2>{item.id ? 'Edit EndPoint' : 'Add EndPoint'}</h2>;

    return <div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="url">URL</Label>
            <Input type="text" name="url" id="url" value={item.url || 'http://www.'}
                   onChange={this.handleChange} autoComplete="url"/>
          </FormGroup>

          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/endpoints">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default withRouter(EndPointEdit);