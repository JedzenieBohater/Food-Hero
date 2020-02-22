import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'
import logo from "../../../static/images/logolarge.png"
import user from './userinstance.json'
import List from "../Search/List"

const mapStateToProps = ({ languageReducer }) => ({
	lang: languageReducer.profile
})

export const Profile = (props) => {
	const [view, setView] = useState('profile')
	const [email, setEmail] = useState(user.email)
	const [firstname, setFirstname] = useState(user.firstname)
	const [lastname, setLastname] = useState(user.lastname)
	const [description, setDescription] = useState(user.description)
	const [specialization, setSpecialization] = useState(user.specialization)


	const handleSubmit = async event => {
		event.preventDefault()
		//zaslepka
	}

	let activePanel =
		<table className="tableform">
			<tbody>
				<tr>
					<td>
						<label htmlFor='name'>{props.lang.name}: </label>
					</td>
					<td>
						<label >{user.firstname}</label>
					</td>
				</tr>
				<tr>
					<td>
						<label >{props.lang.surname}: </label>
					</td>
					<td>
						<label >{user.lastname}</label>
					</td>
				</tr>
				<tr>
					<td>
						<label >{props.lang.noactive}: </label>
					</td>
					<td>
						<label >{user.noactive}</label>
					</td>
				</tr>
				<tr>
					<td>
						<label >{props.lang.nowaiting}:</label>
					</td>
					<td>
						<label >{user.nowaiting}</label>
					</td>
				</tr>
				<tr>
					<td>
						<label >{props.lang.specialization}: </label>
					</td>
					<td>
						<label >{user.specialization}</label>
					</td>
				</tr>
				<tr>
					<td>
						<label >{props.lang.creation}: </label>
					</td>
					<td>
						<label >{user.creation_date}</label>
					</td>
				</tr>
				<tr>
					<td>
						<label >{props.lang.avgrade}:</label>
					</td>
					<td>
						<div class="star-ratings-css-profile">
							<div class="star-ratings-css-top" style={{ width: user.avgrade / 5 * 100 + '%' }}><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
							<div class="star-ratings-css-bottom"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<label >{props.lang.description}:</label>
					</td>
					<td>
						<label >&nbsp;&nbsp;&nbsp;{user.description}</label>
					</td>
				</tr>
			</tbody>
		</table>
	if (view === 'dishlist') {
		activePanel =
			<>
				{user.dishlist.map(dish => (<List
					id={dish.id}
					picture={dish.picture}
					title={dish.title}
					wystawiajacy={dish.cook}
					data={dish.date}
					lokalizacja={dish.location}
					ocena={dish.grade}
					opis={dish.description}
				></List>))}
			</>
	}
	else if (view === 'settings') {
		activePanel =
			<form>
				<table className="tableform">
					<tbody>
						<tr>
							<td>
								<label htmlFor="email">{props.lang.email}:</label>
							</td>
							<td>
								<input onChange={e => setEmail(e.target.value)} type="email" id="email" placeholder={props.lang.email.toLowerCase()} value={email} />
							</td>
						</tr>
						<tr>
							<td>
								<label htmlFor="firstname">{props.lang.name}:</label>
							</td>
							<td>
								<input onChange={e => setFirstname(e.target.value)} type="text" id="firstname" placeholder={props.lang.name.toLowerCase()} value={firstname} />
							</td>
						</tr>
						<tr>
							<td>
								<label htmlFor="lastname">{props.lang.surname}:</label>
							</td>
							<td>
								<input onChange={e => setLastname(e.target.value)} type="text" id="lastname" placeholder={props.lang.surname.toLowerCase()} value={lastname} />
							</td>
						</tr>
						<tr>
							<td>
								<label htmlFor="specialization">{props.lang.specialization}:</label>
							</td>
							<td>
								<input onChange={e => setSpecialization(e.target.value)} type="text" id="specialization" placeholder={props.lang.specialization.toLowerCase()} value={specialization} />
							</td>
						</tr>
						<tr>
							<td>
								<label htmlFor="description">{props.lang.description}:</label>
							</td>
							<td>
								<input onChange={e => setDescription(e.target.value)} type="text" id="description" placeholder={props.lang.description.toLowerCase()} value={description} />
							</td>
						</tr>
						<tr>
							<td colSpan="2">
								<button
									className="btn-blue"
									onClick={handleSubmit}
									disabled={true ? '' : 'disabled'}
								>
									{props.lang.submit}
								</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
	}

	return (
		<div className="profilemain">
			<div className='content-box col25prof'>
				<img className="logolarge" alt="Logo" src={logo} />
				<button className="btn-blue-small" onClick={() => setView('profile')}>{props.lang.profile}</button>
				<button className="btn-blue-small" onClick={() => setView('dishlist')}>{props.lang.dishlist}</button>
				<button className="btn-blue-small" onClick={() => setView('settings')}>{props.lang.settings}</button>
			</div>
			<div className='content-box col75prof'>
				{activePanel}
			</div>
		</div>
	)
}


export default connect(mapStateToProps)(Profile)