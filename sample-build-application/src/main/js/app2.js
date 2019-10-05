var React = require('react');
var ReactDOM = require('react-dom');
var axios = require('axios');
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {builds: []};
    }

    componentDidMount() {
        axios.get('/api/builds')
            .then(
                result => {
                console.info(JSON.stringify(result));
                    this.setState({
                        builds: result.data
                    });
                }
            )
    }

    render() {
        return (
            <BuildList builds={this.state.builds}/>
        )
    }
}

class BuildList extends React.Component {
    render() {
        console.info(JSON.stringify(builds));
        var builds = this.props.builds.map(build =>
            <Build key={build.id} build={build}/>
        );
        return (
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>ID</TableCell>
                        <TableCell>NAME</TableCell>
                        <TableCell>GIT ADDRESS</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {builds}
                </TableBody>
            </Table>
        )
    }
}

class Build extends React.Component {
    render() {
        return (
            <TableRow>
                <TableCell>{this.props.build.id}</TableCell>
                <TableCell>{this.props.build.name}</TableCell>
                <TableCell>{this.props.build.gitAddress}</TableCell>
            </TableRow>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)