import React from 'react';

import ClienteHeader from './ClienteHeader';

class Cliente extends React.Component {

    constructor(props) {
        super();
    }

    render() {
        return (           
            <div>
                <ClienteHeader/>
            </div>
        );
    }
}


export default Cliente;