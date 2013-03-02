/*
 * @Copyright 2013 - ALL RIGHTS RESERVED TO SOURCEPHERE
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.sourcesphere.core.util;

import java.util.Comparator;
import java.util.Map;

/**
 * Strategy interface for <code>String</code>-based path matching.
 *
 *
 * <p>The default implementation is {@link ResourcePathMatcher}, supporting the
 * Ant-style pattern syntax.
 *
 * @author Guilherme Dio
 * @see ResourcePathMatcher
 */
public interface PathMatcher 
{

	/**
	 * Does the given <code>path</code> represent a pattern that can be matched
	 * by an implementation of this interface?
	 */
	boolean isPattern(String path);

	/**
	 * Match the given <code>path</code> against the given <code>pattern</code>
	 * @param pattern The pattern to be used
	 * @param path The path to be verified
	 */
	boolean match(String pattern, String path);

	/**
	 * Match the given <code>path</code> against the corresponding part of the given
	 * <code>pattern</code>
	 * @param pattern The pattern to be used
	 * @param path The path to be verified
	 */
	boolean matchStart(String pattern, String path);

	/**
	 * Given a pattern and a full path, determine the pattern-mapped part.
	 * @param pattern the path pattern
	 * @param path the full path to introspect
	 * @return the pattern-mapped part of the given <code>path</code>
	 */
	String extractPathWithinPattern(String pattern, String path);

	/**
	 * Given a pattern and a full path, extract the URI template variables.
	 * @param pattern the path pattern, possibly containing URI templates
	 * @param path the full path to extract template variables from
	 * @return a map, containing variable names as keys; variables values as values
	 */
	Map<String, String> extractUriTemplateVariables(String pattern, String path);

	/**
	 * Given a full path, returns a {@link Comparator}
	 * @param path the full path to use for comparison
	 * @return a comparator capable of sorting patterns in order of explicitness
	 */
	Comparator<String> getPatternComparator(String path);

	/**
	 * Combines two patterns into a new pattern that is returned.
	 * <p>The full algorithm used for combining the two pattern depends on the underlying implementation.
	 * @param pattern1 the first pattern
	 * @param pattern2 the second pattern
	 * @return the combination of the two patterns
	 * @throws IllegalArgumentException when the two patterns cannot be combined
	 */
	String combine(String pattern1, String pattern2);

}
