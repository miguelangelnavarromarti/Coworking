import googleMapReact from "google-map-react";
import React, { Component } from "react";
import {Carousel, CarouselItem, CarouselIndicators, CarouselCaption, CarouselControl,Col,} from 'reactstrap';
import logo from '../logoBandera.png';
import foto1 from '../img/utopicusgranvia20.webp';
import foto2 from '../img/utopicusgranvia3.webp';
import foto3 from '../img/utopicusgranvia5.webp';
import foto4 from '../img/utopicusgranvia11.webp';
import GoogleMapReact from 'google-map-react';

//          POSAR SES NOSTRES FOTOS
const items = [
	{
		src: foto2,
		altText: 'Espai 1',		
	},
	{
		src: foto3,
		altText: 'Espai 2',		
	},
	{
		src: foto4,	
		altText: 'Espai 3',		
	}
];

class Home extends Component {

    constructor(props) {
		super(props);
		this.state = { activeIndex: 0 };
		this.next = this.next.bind(this);
		this.previous = this.previous.bind(this);
		this.goToIndex = this.goToIndex.bind(this);
		this.onExiting = this.onExiting.bind(this);
		this.onExited = this.onExited.bind(this);
	}

    onExiting() {
		this.animating = true;
	}

	onExited() {
		this.animating = false;
	}

	next() {
		if (this.animating) return;
		const nextIndex = this.state.activeIndex === items.length - 1 ? 0 : this.state.activeIndex + 1;
		this.setState({ activeIndex: nextIndex });
	}

	previous() {
		if (this.animating) return;
		const nextIndex = this.state.activeIndex === 0 ? items.length - 1 : this.state.activeIndex - 1;
		this.setState({ activeIndex: nextIndex });
	}

	goToIndex(newIndex) {
		if (this.animating) return;
		this.setState({ activeIndex: newIndex });
	}

	render() {
		const { activeIndex } = this.state;

		const slides = items.map((item) => {
			return (
				<CarouselItem
					onExiting={this.onExiting}
					onExited={this.onExited}
					key={item.src}                                       
				>
					<img src={item.src} alt={item.altText} className=" mx-auto d-block"/>
					<CarouselCaption captionText={item.caption} captionHeader={item.caption} />
				</CarouselItem>
			);
		});
		const center = {
			lat: 39.56734932417211,
			lng: 3.2167678392753696
		  }
		const greatPlaceStyle = {
			position: 'absolute',
			tranform: 'translate(-50%, -50%)'
		}
		const Marker = props => {
			return (<img style={greatPlaceStyle} className={"marker-map"} height= '80px' width='100px' src={logo} alt={"Localitzación"} />)
		}

		return (
			<div className="bg-white pb-5">								
                <Col sm={9} className="mx-auto my-3">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                    Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. </p>			
                </Col>

                <img src={foto1} className="mx-auto d-block " alt="Imatge Oficina" height="600px" />

                <Col sm={9} className="mx-auto my-3">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                    Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. </p>			
                </Col>	
                
				<Carousel
					activeIndex={activeIndex}
					next={this.next}
					previous={this.previous}
                    interval={7000}
                    className="bg-degradatInvertit p-2"
				>
					<CarouselIndicators items={items} activeIndex={activeIndex} onClickHandler={this.goToIndex} />
					{slides}
					<CarouselControl direction='prev' directionText='Previous' onClickHandler={this.previous} />
					<CarouselControl direction='next' directionText='Next' onClickHandler={this.next} />
				</Carousel>
				<div className={'mt-5'} style={{height: '300px', width: '100%'}}>
					<GoogleMapReact
						bootrstrapURLKey={{ key: "AIzaSyBCKiIqCdZGrVxx06LSbe7uG3zXOq1Cz5k"}}	
						center={center}
						zoom={18}
						>
							<Marker lat='39.56742413987475' lng='3.2167855623012747' iconAnchor ={[17,46]}/>
						</GoogleMapReact>
				</div>
			</div>
		);
	}
}

export default Home;