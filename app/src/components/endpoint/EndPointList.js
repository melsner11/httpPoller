import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from '../AppNavbar';
import { Link } from 'react-router-dom';

class EndPointList extends Component {

  constructor(props) {
    super(props);
    this.state = {endpoints: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('api/endpoints')
      .then(response => response.json())
      .then(data => this.setState({endpoints: data, isLoading: false}));
  }

  async remove(id) {
    await fetch(`/api/endpoint/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedEndPoints = [...this.state.endpoints].filter(i => i.id !== id);
      this.setState({endpoints: updatedEndPoints});
    });
  }

  async restart() {
    await fetch(`/api/restart/`, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    });
  }

  render() {
    const {endpoints, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const getDateString = timestamp => {
        const plus0 = num => `0${num.toString()}`.slice(-2)

        const d = new Date(timestamp)

        const year = d.getFullYear()
        const monthTmp = d.getMonth() + 1
        const month = plus0(monthTmp)
        const date = plus0(d.getDate())
        const hour = plus0(d.getHours())
        const minute = plus0(d.getMinutes())
        const second = plus0(d.getSeconds())
        const rest = timestamp.toString().slice(-5)

        return `${date}-${month}-${year} ${hour}:${minute}:${second}`
    }

    const endpointList = endpoints.map(endpoint => {
      //const url = `${endpoint.url || ''}`;
      console.log("endpoint: " + endpoint);

      return <tr key={endpoint.id}>
        <td style={{whiteSpace: 'nowrap'}}>{endpoint.url}</td>
        <td>{(endpoint.currentStatus != null) ? endpoint.currentStatus.status : ''}</td>
        <td>{(endpoint.currentStatus != null) ? endpoint.currentStatus.reason : ''}</td>
        <td>{(endpoint.currentStatus != null) ? getDateString(endpoint.currentStatus.timestamp) : '' }</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/endpoint/" + endpoint.id}>Edit</Button>
            <Button size="sm" color="danger" onClick={() => this.remove(endpoint.id)}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <div className="float-right">
              <Button color="success" tag={Link} to="/endpoint/new">Add EndPoint</Button>
          </div>
          <div className="float-right">
             <Button color="primary" onClick={() => this.restart()} className="button-restart">Restart Poller</Button>
          </div>
          <h3>EndPoints</h3>
          <Table className="mt-4">
            <thead>
            <tr>
              <th width="20%">URL</th>
              <th width="5%">Status</th>
              <th width="45%"></th>
              <th width="20%">Time</th>
              <th width="10%">Actions</th>
            </tr>
            </thead>
            <tbody>
            {endpointList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default EndPointList;